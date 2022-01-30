package com.satvik.bookexchange.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "COMMUNITY", schema = "dbo")
@Builder
public class Community {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private int creator_id;

    private Double latitude;

    private Double longitude;

    private String city;

    private String state;

    private String country;

    @Column(name="num_users")
    private int numUsers;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "community")
    private Set<UserXCommunity> userXCommunities;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "community")
    private Set<Post> posts;
}
