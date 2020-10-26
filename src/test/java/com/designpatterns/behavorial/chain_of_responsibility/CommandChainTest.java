package com.designpatterns.behavorial.chain_of_responsibility;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://en.wikipedia.org/wiki/Chain-of-responsibility_pattern
 */
class CommandChainTest {

    @Test
    public void processorsCanDefineCommandsToHandle() throws InvalidInputException {

        ProcessorChain commandChain = new ProcessorChain(
            context -> {
                if ("say nothing".equals(context.getCommand())) {
                    context.setOutput("...");
                }
            },
            context -> {
                if ("say something".equals(context.getCommand())) {
                    context.setOutput("chuck norris");
                }
            },
            context -> context.setOutput("hello world")
        );

        String output = commandChain.handleInput("say something");

        Assertions.assertEquals("chuck norris", output);
    }

    @Test
    public void newProcessorsCanBeAdded() throws InvalidInputException {
        ProcessorChain processorChain = new ProcessorChain();
        processorChain.addProcessor(
            context -> context.setOutput("hello world")
        );

        String output = processorChain.handleInput("hey");

        Assertions.assertEquals("hello world", output);
    }

    @Test
    public void theChainThrowsIfNoHandlerResolved() {
        ProcessorChain commandChain = new ProcessorChain(
            context -> {
                if ("a".equals(context.getCommand())) {
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