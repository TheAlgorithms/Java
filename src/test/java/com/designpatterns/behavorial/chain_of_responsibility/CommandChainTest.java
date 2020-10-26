package com.designpatterns.behavorial.chain_of_responsibility;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CommandChainTest {

    @Test
    public void theChainReturnsByFirstFittingHandler() throws InvalidInputException {

        CommandChain commandChain = new CommandChain(
            context -> {/* do nothing */},
            context -> context.setOutput("chuck norris"),
            context -> context.setOutput("hello world")
        );

        String output = commandChain.handleInput("say something");

        Assertions.assertEquals("chuck norris", output);
    }

    @Test
    public void theChainThrowsIfNoHandlerResolved() {
        CommandChain commandChain = new CommandChain(
            context -> {
                if ("a".equals(context.getInput())) {
                    context.setOutput("b");
                }
            }
        );
        try {
            String output = commandChain.handleInput("c");
            Assertions.fail("expected method to throw");

        } catch (InvalidInputException e) {
            // all okay
        }
    }
}