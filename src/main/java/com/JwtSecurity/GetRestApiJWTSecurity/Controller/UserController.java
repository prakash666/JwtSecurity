package com.JwtSecurity.GetRestApiJWTSecurity.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
@RequestMapping
public class UserController {


    @GetMapping("/post")
    public String getData(){
        return "Hello World";
    }

}
