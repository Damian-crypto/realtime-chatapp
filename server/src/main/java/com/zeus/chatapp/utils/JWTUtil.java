package com.zeus.chatapp.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTUtil {
    
    private final String SECRET_KEY = "topsecret";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * This method allows you to extract a specific claim from a JWT by providing
     * a function that knows how to extract the desired information from the
     * Claims object.
     * 
     * @param <T>
     * @param token JWT
     * @param claimResolver method that knows how to extract specific claim
     * @return required claim from the JWT
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);

        return claimResolver.apply(claims);
    }

    /**
     * This method extracts the payload of the JSON Web Token
     * 
     * @param token JSON Web Token
     * @return content of the JWT
     */
    public Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                // Sets the signing key that will be used to verify the signature of the JWT
                .setSigningKey(SECRET_KEY)
                // If the signature is valid, it returns a Jws<Claims> object,
                // where Claims represents the payload of the JWT
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * This method grab the date of the expiration of the JWT
     * 
     * @param token JWT
     * @return expiration date
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * This function generates and returns the JWT for the provided
     * userDetails
     * 
     * @param userDetails user details going to be authenticated
     * @return JWT
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        return createToken(claims, userDetails.getUsername());
    }

    /**
     * This function creates and returns a JSON Web Token for a subject/person.
     * 
     * @param claims Extract claims for the subject
     * @param subject The user going to be authenticated
     * @return JSON Web Token
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 3600 * 12)) // 12 hours
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    /**
     * This method ensures that `userDetails.username` exactly matches with the
     * provided `token.username`.
     * 
     * @param token JWT
     * @param userDetails user information from the login
     * @return valid user or not
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * This method checks whether `token` is expired or not.
     * 
     * @param token JWT
     * @return expired or not
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
