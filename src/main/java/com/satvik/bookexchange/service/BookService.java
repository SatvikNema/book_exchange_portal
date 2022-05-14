package com.satvik.bookexchange.service;

import com.satvik.bookexchange.entity.Book;
import com.satvik.bookexchange.pojo.BookResponse;
import com.satvik.bookexchange.repository.BookRepository;
import com.satvik.bookexchange.util.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Flux<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookResponse> bookResponses = books.stream().map(ObjectMapperUtil::convertToDto).collect(Collectors.toList());
        return Flux.fromIterable(bookResponses);
    }
}
