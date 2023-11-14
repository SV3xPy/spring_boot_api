package com.example.crud_shopall.security;

import com.example.crud_shopall.model.Rol;
import com.example.crud_shopall.model.UsuarioRol;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

public class TokenUtils {

    private final static String ACCESS_TOKEN_SECRET = "7yXp5d2aW8rGhEeTzRyU0fFp3iQ9wXhL0lgeyjjm09lGMCQxDRvzpoO";//"kN5pLQvyGt2bmZJ9iUo1XwxRD";
    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;

    public static String createToken(String nombre, String email, List<Rol> roles){
        Long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Object> extra = new HashMap<>();
        extra.put("nombre", nombre);
        extra.put("roles", roles);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token){
        try{
            Claims claims = Jwts.parserBuilder() //Esto será un proceso inverso a lo que se hacía al crear el token
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String email = claims.getSubject();
            //List<Rol> roles = (List<Rol>) claims.get("roles");
            List<LinkedHashMap<String, String>> rolesMap = (List<LinkedHashMap<String, String>>) claims.get("roles");
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for (LinkedHashMap<String, String> roleMap : rolesMap) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + roleMap.get("rol")));
                System.out.println("ROLE_"+roleMap.get("rol"));
            }
            return new UsernamePasswordAuthenticationToken(email, null, authorities);
        } catch (JwtException e){
            return null;
        }

    }

}
