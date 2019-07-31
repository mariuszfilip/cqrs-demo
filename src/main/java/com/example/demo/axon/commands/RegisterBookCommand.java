package com.example.demo.axon.commands;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
public class RegisterBookCommand {
    @TargetAggregateIdentifier
    private Integer libraryId;
    private String isbn;
    private String title;

    public RegisterBookCommand(Integer libraryId, String isbn, String title) {
        this.libraryId = libraryId;
        this.isbn = isbn;
        this.title = title;
    }
}
