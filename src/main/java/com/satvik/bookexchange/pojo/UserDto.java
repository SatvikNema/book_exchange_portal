package com.satvik.bookexchange.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class UserDto {
    public UserDto(){}
    private Integer id;
    private String email;
    private String name;
    private String username;
    private String password;
    private String mobile;
    private Date dob;
    private Date created;
    private Boolean accVerified;
}
