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
import java.util.NoSuchElementException;
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
        if(!user.getUserXBooks().isEmpty()){
            user.getUserXBooks().forEach(userXBook -> {
                System.out.println(userXBook.getBook().getTitle());
            });
        } else {
            log.info("No books owned by the user!");
        }
        return null;
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

    public UserDto getUser(int userId) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        System.out.println("books owned: ===================================");
        user.getUserXBooks().forEach(userXBook -> {
            System.out.println(userXBook.getBook().getTitle());
        });

        System.out.println("communities: =================================");
        user.getUserXCommunities().forEach(userXCommunity -> {
            System.out.println(userXCommunity.getCommunity().getName());
        });

        System.out.println("posts: ================================");
        user.getPosts().forEach(p -> {
            System.out.println(p.getTitle());
        });
        return convertUserToDto(user);
    }


}
