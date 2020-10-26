package com.designpatterns.behavorial.chain_of_responsibility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcessorChain {

    private final List<CommandProcessor> commandProcessors = new ArrayList<>();

    public ProcessorChain(CommandProcessor...commandProcessors) {
        Collections.addAll(this.commandProcessors, commandProcessors);
    }

    public void addProcessor(CommandProcessor commandProcessor) {
        this.commandProcessors.add(commandProcessor);
    }

    public String handleInput(String input) throws InvalidInputException {
        CommandContext context = new CommandContext(input);

        for (CommandProcessor command : commandProcessors) {
            command.handle(context);

            if (context.hasOutput()) {
                return context.output;
            }
        }

        throw new InvalidInputException();
    }

}
