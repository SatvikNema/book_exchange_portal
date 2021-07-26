package com.satvik.bookexchange.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "COMMENT", schema = "dbo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comment {
    @JsonProperty("postId")
    @Column(name = "postId")
    private int postId;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("body")
    private String body;
}
