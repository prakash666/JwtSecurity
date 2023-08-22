package com.JwtSecurity.GetRestApiJWTSecurity.Controller;


import com.JwtSecurity.GetRestApiJWTSecurity.Configurations.JavaTokenUtil;
import com.JwtSecurity.GetRestApiJWTSecurity.Service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JavaTokenUtil javaTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping (value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken
            (@RequestBody JwtRequest authenticationRequest){

    }
}
