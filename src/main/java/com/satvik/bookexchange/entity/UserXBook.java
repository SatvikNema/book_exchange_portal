package com.satvik.bookexchange.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "USER_BOOK")
@NoArgsConstructor
@Builder
@Getter
@Setter
@AllArgsConstructor
public class UserXBook {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @Column(name = "user_book_category")
    private String userBookCategory;

}
