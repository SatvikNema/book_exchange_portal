package com.satvik.bookexchange.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="ROLE", schema="dbo")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="role_name")
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

}
