package com.example.endterm_project.security;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;

public class JwtKeyGenerator {
    public static void main(String[] args) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        System.out.println("Generated key: " + java.util.Base64.getEncoder().encodeToString(key.getEncoded()));
    }
}
