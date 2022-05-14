package com.satvik.bookexchange.util;

import com.satvik.bookexchange.entity.Book;
import com.satvik.bookexchange.pojo.BookResponse;

public class ObjectMapperUtil {

    private ObjectMapperUtil(){}

    public static BookResponse convertToDto(Book original){
        return BookResponse.builder()
                .id(original.getId())
                .author(original.getAuthor())
                .description(original.getDescription())
                .isbn(original.getIsbn())
                .title(original.getTitle())
                .build();
    }
}
