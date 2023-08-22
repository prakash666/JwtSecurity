package com.JwtSecurity.GetRestApiJWTSecurity.Configurations;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JavaTokenUtil implements Serializable {
    @Serial
    private static final long serialVersionUID =-2550185165626007488L;
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 *60;

    @Value( "${jwt.secret_key")         // This is means calling the secret key which we have defined in application.properties file
    private String secret;              // This is declaring variable String variable called secret

 // retrieving username from Jwt token
 public String getUsernameFromToken(String token){
     return getClaimFromToken(token, Claims::getSubject);
 }

 //retrieving expiration date from Jwt Token
    public Date getExpirationDateFromToken(String token){
     return getClaimFromToken(token,Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
     final Claims claims = getAllClaimsFromToken(token);
     return claimsResolver.apply(claims);
    }

    // for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token){                // In this process we will get all information of user we decode information using an secret_key
     return Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).getBody();
    }

    // Check if the token is expired
    private Boolean isTokenExpired (String token){
     final Date expiration = getExpirationDateFromToken(token);
     return expiration.before(new Date());
    }

    // generate token for user
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims,userDetails.getUsername());
    }


    // While creating an Token
    private String doGenerateToken (Map<String, Object> claims,String subject){

     return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
             .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
             .signWith(SignatureAlgorithm.ES512,secret).compact();
    }

    // validate token
    public Boolean validateToken(String token, UserDetails userDetails){
     final String username = getUsernameFromToken(token);
     return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }













}
