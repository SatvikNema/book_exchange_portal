package com.satvik.bookexchange.pojo;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String email;
    private String password;

    public AuthenticationRequest(){

    }
}
