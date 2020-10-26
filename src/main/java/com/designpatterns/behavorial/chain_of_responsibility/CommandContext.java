package com.designpatterns.behavorial.chain_of_responsibility;

public class CommandContext {

    public final String command;
    public String output;

    public CommandContext(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public boolean hasOutput() {
        return output != null;
    }
}
