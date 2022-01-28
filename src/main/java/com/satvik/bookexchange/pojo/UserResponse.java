package com.satvik.bookexchange.pojo;

import com.satvik.bookexchange.entity.Book;
import com.satvik.bookexchange.entity.Role;

import java.util.Date;
import java.util.Set;

public class UserResponse {
    private Integer id;

    private String email;

    private String name;

    private String password;

    private String mobile;

    private Date dob;

    private Date created;

    private Boolean accVerified;

    private Set<Role> roles;

    private Set<Book> booksOwned;
}
