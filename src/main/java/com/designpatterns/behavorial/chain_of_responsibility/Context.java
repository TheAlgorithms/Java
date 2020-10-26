package com.designpatterns.behavorial.chain_of_responsibility;

public class Context {

    public final String input;
    public String output;

    public Context(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
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
