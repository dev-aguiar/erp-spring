package com.devaguiar.erp.services;

import com.devaguiar.erp.entities.Usuario;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private SecretKey key() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String gerarToken(Usuario usuario) {
        Instant agora = Instant.now();
        return Jwts.builder()
                .issuer("erp-api")
                .subject(usuario.getLogin())
                .issuedAt(Date.from(agora))
                .expiration(Date.from(agora.plus(2, ChronoUnit.HOURS)))
                .signWith(key())
                .compact();
    }

    public String validarToken(String token) {
        try {
            return Jwts.parser().verifyWith(key()).build()
                    .parseSignedClaims(token).getPayload().getSubject();
        } catch (JwtException e) {
            return "";
        }
    }
}