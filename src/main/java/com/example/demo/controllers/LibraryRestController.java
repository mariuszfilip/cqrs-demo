package com.example.demo.controllers;

import com.example.demo.axon.Library;
import com.example.demo.axon.commands.RegisterBookCommand;
import com.example.demo.axon.commands.RegisterLibraryCommand;
import com.example.demo.axon.queries.GetBooksQuery;
import com.example.demo.axon.queries.GetLibraryQuery;
import com.example.demo.dto.BookBean;
import com.example.demo.dto.LibraryBean;
import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class LibraryRestController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @Autowired
    public LibraryRestController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/api/library")
    public String addLibrary(@RequestBody LibraryBean library) {
        commandGateway.send(new RegisterLibraryCommand(library.getLibraryId(), library.getName()));
        return "Saved";
    }

    @PostMapping("/api/library/{library}/book")
    public String addBook(@PathVariable Integer library, @RequestBody BookBean book) {
        commandGateway.send(new RegisterBookCommand(library, book.getIsbn(), book.getTitle()),
                LoggingCallback.INSTANCE);
        return "Added";
    }
    @GetMapping("/api/library/{library}/book")
    public List<BookBean> addBook(@PathVariable Integer library) throws InterruptedException, ExecutionException {
        return queryGateway.query(new GetBooksQuery(library), ResponseTypes.multipleInstancesOf(BookBean.class)).get();
    }
    @GetMapping("/api/library/{library}")
    public Library getLibrary(@PathVariable Integer library) throws InterruptedException, ExecutionException {
        CompletableFuture<Library> future = queryGateway.query(new GetLibraryQuery(library), Library.class);
        return future.get();
    }
}
