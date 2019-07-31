package com.example.demo.axon.events;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
public class LibraryCreatedEvent {

    @TargetAggregateIdentifier
    private Integer libraryId;

    private String name;

    public LibraryCreatedEvent(Integer libraryId, String name) {
        this.libraryId=libraryId;
        this.name=name;
    }


}
