package com.satvik.bookexchange.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommunityAddRequest {
    private String name;
    private int creatorId; // todo remove this after jwt impl
    private double latitude;
    private double longitude;
    private String city;
    private String state;
    private String country;
}
