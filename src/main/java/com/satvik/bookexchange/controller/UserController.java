package com.satvik.bookexchange.controller;

import com.satvik.bookexchange.entity.Book;
import com.satvik.bookexchange.pojo.BookResponse;
import com.satvik.bookexchange.pojo.UserDto;
import com.satvik.bookexchange.service.UserMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    @Autowired
    private UserMetadataService userMetadataService;

    @GetMapping("/user/{id}/books")
    public ResponseEntity<List<BookResponse>> getUsersOwnedBooks(@PathVariable("id") int userId){
        Set<Book> userBooks = userMetadataService.fetchAllBooks(userId);
        List<BookResponse> response = new ArrayList<>();
        userBooks.forEach(book ->
            response.add(BookResponse.builder().id(book.getId())
                    .author(book.getAuthor())
                    .title(book.getTitle())
                    .description(book.getDescription())
                    .isbn(book.getIsbn())
                    .build())
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{id}/book/{book_id}")
    public ResponseEntity<String> addOwnedBookToUser(@PathVariable("id") int userId,
                                                    @PathVariable int bookId){
        String response = "added book!";
//        userMetadataService.addOwnedBook(userId, book_id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/users")
    public Flux<UserDto> getUsers(){
        return userMetadataService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public Mono<UserDto> getUser(@PathVariable("id") int userId){
        return userMetadataService.getUser(userId);
    }
}
