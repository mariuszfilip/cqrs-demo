package com.example.demo.axon.repository;

import com.example.demo.axon.events.BookCreatedEvent;
import com.example.demo.axon.queries.GetBooksQuery;
import com.example.demo.dto.BookBean;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BookRepositoryProjector {
    private final BookRepository bookRepository;

    @Autowired
    public BookRepositoryProjector(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventHandler
    public void addBook(BookCreatedEvent event) throws Exception {
        BookEntity book = new BookEntity();
        book.setIsbn(event.getIsbn());
        book.setLibraryId(event.getLibraryId());
        book.setTitle(event.getTitle());
        bookRepository.save(book);
    }

    @QueryHandler
    public List<BookBean> GetBooksQuery(GetBooksQuery query) {
        return bookRepository.findByLibraryId(query.getLibraryId()).stream().map(toBook()).collect(Collectors.toList());
    }
    private Function<BookEntity, BookBean> toBook() {
        return e -> {
            BookBean book = new BookBean();
            book.setIsbn(e.getIsbn());
            book.setTitle(e.getTitle());
            return book;
        };
    }
}
