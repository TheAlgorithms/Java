package com.designpatterns.behavorial.chain_of_responsibility;

public interface CommandProcessor {

    void handle(CommandContext context);
}
