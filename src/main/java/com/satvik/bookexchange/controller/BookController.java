package com.satvik.bookexchange.controller;

import com.satvik.bookexchange.pojo.BookResponse;
import com.satvik.bookexchange.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public Flux<BookResponse> getAllBooks(){
        return bookService.getAllBooks();
    }
}
