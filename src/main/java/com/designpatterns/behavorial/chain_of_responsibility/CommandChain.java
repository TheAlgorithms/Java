package com.designpatterns.behavorial.chain_of_responsibility;

public class CommandChain {

    private final Command[] commands;

    public CommandChain(Command ...commands) {

        this.commands = commands;
    }

    public String handleInput(String input) throws InvalidInputException {
        Context context = new Context(input);

        for (Command command : commands) {
            command.handle(context);

            if (context.hasOutput()) {
                return context.output;
            }
        }

        throw new InvalidInputException();
    }

}
