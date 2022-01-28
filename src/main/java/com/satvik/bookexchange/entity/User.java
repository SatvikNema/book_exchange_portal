package com.satvik.bookexchange.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="USER", schema="dbo")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String name;

    private String password;

    private String mobile;

    private Date dob;

    private Date created = new Date();

    @Column(name="acc_verified")
    private Boolean accVerified;

    @ManyToMany(cascade=CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name="USER_ROLE",
            joinColumns={@JoinColumn(name="user_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="id")})
    private Set<Role> roles;

    @ManyToMany(cascade=CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name="USER_COMMUNITY",
            joinColumns={@JoinColumn(name="user_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="community_id", referencedColumnName="id")})
    Set<Community> userCommunities;

    @ManyToMany(cascade=CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "USER_BOOK",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")}
    )
    private Set<Book> booksOwned;

}
