package com.example.Securityprueba.service.userServices;

import com.example.Securityprueba.entities.UserModels.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    private final String SECRET_KEY = "e1cc5a762cfd1fa829637fca290f5d99bcc65edd6b48a706c8ef01f7aff15616";

    public String extractUsername(String token){
        return  extractClaim(token, Claims::getSubject);
    }
    public boolean isValid(String token, UserDetails user){
        String username = extractUsername(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }
    public  <T>T extractClaim (String token, Function<Claims,T> resolver)
    {
        Claims claims
                = extractAllClaims(token);
        return resolver.apply(claims);

    }
    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token).getPayload();
    }



    public String generateToken(Users user){
        String token = Jwts.builder()

                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 24*60*60*100))
                .signWith(getSigninKey())
                .compact();
        return token;

    }


    private SecretKey getSigninKey(){
        byte[] keyBytes= Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);

    }}