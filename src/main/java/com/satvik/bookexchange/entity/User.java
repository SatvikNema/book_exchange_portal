package com.satvik.bookexchange.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="USERS", schema="dbo")
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

    @ManyToMany(cascade=CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name="role_user",
            joinColumns={@JoinColumn(name="user_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="id")})
    private Set<Role> roles;
}
