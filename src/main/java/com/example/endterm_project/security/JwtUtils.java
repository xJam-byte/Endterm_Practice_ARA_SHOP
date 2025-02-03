//package com.example.endterm_project.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import java.security.Key;
//import java.util.Date;
//import java.util.function.Function;
//import io.jsonwebtoken.security.Keys;
//import java.nio.charset.StandardCharsets;
//
//public class JwtUtils {
//
//    private final Key key;
//
//    public JwtUtils() {
//        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//    }
//
////    private static final String SECRET_KEY_STR = "your-secret-key";
////
////    private static final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET_KEY_STR.getBytes(StandardCharsets.UTF_8));
//
//    public String generateToken(UserDetails userDetails) {
//        return Jwts.builder()
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 день
//                .signWith(key, SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public static String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private static Claims extractAllClaims(String token) {
//        return Jwts.parser()
//                .setSigningKey(key)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//
//    public Claims decodeToken(String token, String secretKey) {
//        return Jwts.parser()
//                .setSigningKey(secretKey)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    public static boolean validateToken(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
//    }
//
////    public static String generateToken(UserDetails userDetails) {
////        return Jwts.builder()
////                .setSubject(userDetails.getUsername()) // Set the username as the subject
////                .setIssuedAt(new Date()) // Set the issue date to the current date
////                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours expiration
////                .signWith(SignatureAlgorithm.HS512, SECRET_KEY) // Use the secret key to sign the token
////                .compact();
////    }
//
//
//    private static boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    private static Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//}


package com.example.endterm_project.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

public class JwtUtils {

    private final Key key;

    public JwtUtils() {
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    // Генерация токена
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())  // Исправлено
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 день
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Извлечение имени пользователя из токена
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Извлечение любого claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Получение всех claims из токена
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Декодирование токена (дополнительный метод)
    public Claims decodeToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Проверка валидности токена
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    // Проверка истечения токена
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Получение времени истечения токена
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}