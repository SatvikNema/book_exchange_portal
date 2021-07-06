package com.satvik.bookexchange.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "USER_COMMUNITY")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserXCommunity {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "community_id")
    Community community;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @Column(name = "user_role")
    private String userRole;
}
