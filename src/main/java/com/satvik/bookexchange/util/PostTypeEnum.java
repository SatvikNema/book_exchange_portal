package com.satvik.bookexchange.util;

import lombok.Getter;

public enum PostTypeEnum {
    EXCHANGE("EXCHANGE"),
    RANDOM("RANDOM"),
    REVIEW("REVIEW");

    private String value;

    PostTypeEnum(String val){
        this.value = val;
    }

    public String getValue(){
        return this.value;
    }
}
