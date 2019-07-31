package com.example.demo.axon;

import com.example.demo.axon.commands.RegisterBookCommand;
import com.example.demo.axon.commands.RegisterLibraryCommand;
import com.example.demo.axon.events.BookCreatedEvent;
import com.example.demo.axon.events.LibraryCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Aggregate
public class Library {

    @AggregateIdentifier
    private Integer libraryId;
    private String name;
    private List<String> isbnBooks;

    protected Library() {
        // For Axon instantiation
    }

    @CommandHandler
    public Library(RegisterLibraryCommand cmd) {
        Assert.notNull(cmd.getLibraryId(), "ID should not be null");
        Assert.notNull(cmd.getName(), "Name should not be null");

        AggregateLifecycle.apply(new LibraryCreatedEvent(cmd.getLibraryId(), cmd.getName()));
    }

    @EventSourcingHandler
    private void handleCreatedEvent(LibraryCreatedEvent event) {
        libraryId = event.getLibraryId();
        name = event.getName();
        isbnBooks = new ArrayList<>();
    }

    @CommandHandler
    public void addBook(RegisterBookCommand cmd) {
        Assert.notNull(cmd.getLibraryId(), "ID should not be null");
        Assert.notNull(cmd.getIsbn(), "Book ISBN should not be null");

        AggregateLifecycle.apply(new BookCreatedEvent(cmd.getLibraryId(), cmd.getIsbn(), cmd.getTitle()));
    }

    @EventSourcingHandler
    private void addBook(BookCreatedEvent event) {
        isbnBooks.add(event.getIsbn());
    }
}
