package com.example.demo.axon.repository;

import com.example.demo.axon.Library;
import com.example.demo.axon.queries.GetLibraryQuery;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.modelling.command.Aggregate;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.spring.eventsourcing.SpringPrototypeAggregateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class LibraryProjector {
    private EventSourcingRepository eventSourcingRepository;

    @Autowired
    public LibraryProjector(EventSourcingRepository eventSourcingRepository) {
        this.eventSourcingRepository = eventSourcingRepository;
    }

    @QueryHandler
    public Library getLibrary(GetLibraryQuery query) throws InterruptedException, ExecutionException {
        Aggregate load = eventSourcingRepository.load("" + query.getLibraryId());
        return  null;

    }

}