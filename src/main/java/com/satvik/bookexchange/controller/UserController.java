package com.satvik.bookexchange.controller;

import com.satvik.bookexchange.service.UserMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserMetadataService userMetadataService;

    @GetMapping("/user/{id}/books")
    public ResponseEntity<String> getUsersOwnedBooks(@PathVariable("id") int user_id){
        String response = "fetched! see console";
        userMetadataService.fetchAllBooks(user_id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{id}/book/{book_id}")
    public ResponseEntity<String> addOwnedBookToUser(@PathVariable("id") int user_id,
                                                    @PathVariable int book_id){
        String response = "added book!";
        userMetadataService.addOwnedBook(user_id, book_id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
