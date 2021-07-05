package com.satvik.bookexchange.util;

import com.satvik.bookexchange.pojo.MyUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Component
public class JwtUtil {
    private String SECRET_KEY = "satvikHereTesting1234";

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public MyUserDetails extractUserDetails(String token){
        final Claims claims = extractAllClaims(token);
        String username = claims.getSubject();
        List<String> roles = claims.get("roles", ArrayList.class);
        String name = claims.get("name", String.class);
        return MyUserDetails.builder().username(username)
                .roles(roles).name(name).build();
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private <T> T extractClaim(java.lang.String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public String generateToken(MyUserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", userDetails.getName());
        claims.put("roles", userDetails.getRoles());
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder().setClaims(claims).setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (60 * 60 * 1000)))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
