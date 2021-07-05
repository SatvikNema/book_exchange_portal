package com.satvik.bookexchange.pojo;

import com.satvik.bookexchange.entity.Role;
import com.satvik.bookexchange.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
public class MyUserDetails implements UserDetails {

    private String username;

    private String name;

    private String password;

    private List<String> roles;

    public MyUserDetails(){}

    public MyUserDetails(String username){
        this.name = username;
    }

    public MyUserDetails(User user){
        this.name = user.getName();
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.roles = user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList());
        System.out.println(this.toString());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return  username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "MyUserDetails{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }

    public List<String> getRoles(){
        return roles;
    }

    public String getName(){
        return name;
    }
}
