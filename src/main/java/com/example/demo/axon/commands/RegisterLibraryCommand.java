package com.example.demo.axon.commands;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
public class RegisterLibraryCommand {

    @TargetAggregateIdentifier
    private Integer libraryId;

    private String name;

    public RegisterLibraryCommand(Integer libraryId, String name) {
        this.libraryId = libraryId;
        this.name = name;
    }
}
