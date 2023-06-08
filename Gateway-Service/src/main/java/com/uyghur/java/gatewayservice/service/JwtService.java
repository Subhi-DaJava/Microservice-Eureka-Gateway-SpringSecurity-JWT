package com.uyghur.java.gatewayservice.service;

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

    public void validateToken(final String JWT_TOKEN) {
        Jwts.parserBuilder()
                .setSigningKey(getSignatureKey())
                .build()
                .parseClaimsJws(JWT_TOKEN);
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


}
