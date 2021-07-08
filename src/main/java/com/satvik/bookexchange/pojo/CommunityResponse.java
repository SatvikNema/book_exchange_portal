package com.satvik.bookexchange.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityResponse {
    private Integer id;

    private String name;

    private int creator_id;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String city;

    private String state;

    private String country;

    private int numUsers;
}
