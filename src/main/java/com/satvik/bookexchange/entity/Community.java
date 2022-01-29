package com.satvik.bookexchange.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "COMMUNITY", schema = "dbo")
public class Community {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private int creator_id;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String city;

    private String state;

    private String country;

    @Column(name="num_users")
    private int numUsers;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "community")
    Set<UserXCommunity> userXCommunities;
}
