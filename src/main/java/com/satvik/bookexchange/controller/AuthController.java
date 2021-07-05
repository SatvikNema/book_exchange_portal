package com.satvik.bookexchange.controller;

import com.satvik.bookexchange.entity.User;
import com.satvik.bookexchange.pojo.AuthenticationRequest;
import com.satvik.bookexchange.pojo.AuthenticationResponse;
import com.satvik.bookexchange.pojo.MyUserDetails;
import com.satvik.bookexchange.service.AuthService;
import com.satvik.bookexchange.util.JwtUtil;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/user")
    public ResponseEntity<String> getUser(){
        String response = "user";
        SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities()
                .forEach((authority) -> {
                    System.out.println(authority.getAuthority());
                });
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> getAdmin(){
        String response = "admin";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        String response = "recieved";
        authService.register(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest authenticationRequest)throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()));
        }
        catch(BadCredentialsException e){
            System.out.println(e.getMessage());
            throw new Exception("incorrect username password", e);
        }
        final MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String token = jwtUtil.generateToken(userDetails);
        AuthenticationResponse response = new AuthenticationResponse(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/setRole/{email}/{role}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> setRole(@PathVariable String email, @PathVariable String role){
        String response = "new role assinged!";
        System.out.println("allowed!");
        authService.setRole(email, role);
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
