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

    private String username;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    Set<UserXCommunity> userXCommunities;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<UserXBook> userXBooks;

}
