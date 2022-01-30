package com.satvik.bookexchange.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityDto {
    private String name;
    private Double latitude;
    private Double longitude;
    private String city;
    private String state;
    private String country;
    private int numUsers;
}
