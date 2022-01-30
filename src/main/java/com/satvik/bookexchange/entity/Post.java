package com.satvik.bookexchange.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "POST")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String type;
    private String description;

    @Temporal(TemporalType.DATE)
    private Date created;

    @ManyToOne
    @JoinColumn(name="creator_id")
    private User creator;

    @ManyToOne
    @JoinColumn(name="community_id")
    private Community community;


}
