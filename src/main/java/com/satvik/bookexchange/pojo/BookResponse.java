package com.satvik.bookexchange.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse {
    private int id;
    private String title;
    private String author;
    private String description;
    private String isbn;
}
