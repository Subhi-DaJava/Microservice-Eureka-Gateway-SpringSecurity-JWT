package com.uyghur.java.securityservice.jwt_util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtService {
    // refer to repo : https://github.com/Subhi-DaJava/Spring-Security-JWT-Token-Authentication-Authorization/blob/JWT-Implement/src/main/java/com/uyghur/java/userauth/jwt_service/JwtService.java
    public static final String SECRET =
            "586E5A7234753778214125442A472D4B6150645367566B59703373357638792F";

    //https://jwt.io/
    public String generateJwtToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createJwtToken(claims, username);
    }

    private String createJwtToken(Map<String, Object> claims, String username) {
        log.debug("createJwtToken method starts here");
        String jwt_token = Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256).compact();

        log.info("JWT token : {%s} is successfully created".formatted(jwt_token));
        return jwt_token;
    }

    /**
     * Create own Key bit256 with Hex format :
     * 586E5A7234753778214125442A472D4B6150645367566B59703373357638792F
     *
     * @return Key
     */
    private Key getSignatureKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public void validateToken(final String JWT_TOKEN) {
       Jwts.parserBuilder()
               .setSigningKey(getSignatureKey())
               .build()
               .parseClaimsJws(JWT_TOKEN);
    }
}