package com.satvik.bookexchange.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COMMENT", schema = "dbo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Comment {
    private int postId;

    @Id
    private Integer id;
    private String name;
    private String email;
    private String body;
}
