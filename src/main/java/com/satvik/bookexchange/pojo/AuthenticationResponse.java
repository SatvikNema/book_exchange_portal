package com.satvik.bookexchange.pojo;

import lombok.Builder;

public class AuthenticationResponse {

    private String jwtToken;

    public AuthenticationResponse(){}

    public AuthenticationResponse(String token){
        jwtToken = token;
    }

    public String getJwtToken(){
        return jwtToken;
    }

    public void setJwtToken(String token){
        jwtToken = token;
    }
}
