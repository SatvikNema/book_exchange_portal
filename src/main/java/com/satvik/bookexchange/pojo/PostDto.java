package com.satvik.bookexchange.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private String title;
    private String type;
    private String description;
    private Date created;
}
