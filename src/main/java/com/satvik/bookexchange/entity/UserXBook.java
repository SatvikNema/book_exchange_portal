package com.satvik.bookexchange.entity;

import javax.persistence.*;

@Entity
@Table(name = "USER_BOOK")
public class UserXBook {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "user_book_category")
    private String userBookCategory;

}
