package com.satvik.bookexchange.service;

import com.satvik.bookexchange.entity.Book;
import com.satvik.bookexchange.entity.User;
import com.satvik.bookexchange.pojo.UserDto;
import com.satvik.bookexchange.repository.BookRepository;
import com.satvik.bookexchange.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserMetadataService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Set<Book> fetchAllBooks(int user_id) {
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
        return user.getBooksOwned();
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

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertUserToDto)
                .collect(Collectors.toList());
    }

    private UserDto convertUserToDto(User user){
        return modelMapper.map(user, UserDto.class);
    }
}
