package com.rmd.business.ecommerce.controller;

import com.rmd.business.ecommerce.dto.ResponseDto;
import com.rmd.business.ecommerce.dto.user.SignInDto;
import com.rmd.business.ecommerce.dto.user.SignInReponseDto;
import com.rmd.business.ecommerce.dto.user.SignupDto;
import com.rmd.business.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    // two apis

    // signup
    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignupDto signupDto) {
        return userService.signUp(signupDto);
    }

    // signin
    @PostMapping("/signin")
    public SignInReponseDto signIn(@RequestBody SignInDto signInDto) {
        System.out.println("-----> user signing");
        return userService.signIn(signInDto);
    }
}
