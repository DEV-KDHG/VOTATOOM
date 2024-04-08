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


    public Long extractUserId(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("Identification", Long.class); // Suponiendo que "userId" es el nombre del claim que contiene el ID del usuario
    }

    public String generateToken(Users user){
        Long userId = user.getId(); // Suponiendo que tienes un método para obtener el ID del usuario
        return Jwts.builder()
                .claim("identification", userId) // Agregar la identificación del usuario como una claim al token
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 24*60*60*1000)) // Cambiado 24*60*60*100 a 24*60*60*1000 para que sea en milisegundos
                .signWith(getSigninKey())
                .compact();
    }

    private SecretKey getSigninKey(){
        byte[] keyBytes= Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);

    }}