package com.example.demo.axon.events;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
public class BookCreatedEvent {

    @TargetAggregateIdentifier
    private Integer libraryId;
    private String isbn;
    private String title;

    public BookCreatedEvent(Integer libraryId, String isbn, String title) {
        this.libraryId = libraryId;
        this.isbn=isbn;
        this.title=title;
    }
}
