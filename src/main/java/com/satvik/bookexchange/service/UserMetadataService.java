package com.satvik.bookexchange.service;

import com.satvik.bookexchange.entity.Book;
import com.satvik.bookexchange.entity.User;
import com.satvik.bookexchange.repository.BookRepository;
import com.satvik.bookexchange.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@Slf4j
public class UserMetadataService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public void fetchAllBooks(int user_id) {
        log.info("-----------------------------------------");
        User user = userRepository.findById(user_id).get();
        log.info("--------------User's Books--------------");
        if(!user.getBooksOwned().isEmpty()){
            user.getBooksOwned().forEach(book -> {
                System.out.println(book.getTitle());
            });
        } else {
            log.info("No books owned by the user!");
        }
    }

    public User addOwnedBook(int user_id, int book_id) {
        User user = userRepository.findById(user_id).get();
        Book book = bookRepository.findById(book_id).get();
        if(user.getBooksOwned().isEmpty()){
            user.setBooksOwned(new HashSet<>());
        }
        user.getBooksOwned().add(book);
        return userRepository.save(user);
    }
}
