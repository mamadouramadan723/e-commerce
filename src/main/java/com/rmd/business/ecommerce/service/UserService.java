package com.rmd.business.ecommerce.service;

import com.rmd.business.ecommerce.dto.ResponseDto;
import com.rmd.business.ecommerce.dto.user.SignInDto;
import com.rmd.business.ecommerce.dto.user.SignInReponseDto;
import com.rmd.business.ecommerce.dto.user.SignupDto;
import com.rmd.business.ecommerce.exceptions.AuthenticationFailException;
import com.rmd.business.ecommerce.exceptions.CustomException;
import com.rmd.business.ecommerce.model.AuthenticationToken;
import com.rmd.business.ecommerce.model.User;
import com.rmd.business.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Transactional
    public ResponseDto signUp(SignupDto signupDto) {
        // check if user is already present
        if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
            // we have a user
            System.out.println("user already present");
            throw new CustomException("user already present");
        }


        // hash the password

        String encryptedPassword = signupDto.getPassword();

        try {
            encryptedPassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        User user = new User(signupDto.getFirstName(), signupDto.getLastName(),
                signupDto.getEmail(), encryptedPassword);

        userRepository.save(user);

        // save the user

        // create the token

        final AuthenticationToken authenticationToken = new AuthenticationToken(user);

        authenticationService.saveConfirmationToken(authenticationToken);

        return new ResponseDto("success", "user created successfully");
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter
                .printHexBinary(digest).toUpperCase();
    }

    public SignInReponseDto signIn(SignInDto signInDto) {
        // find user by email

        User user = userRepository.findByEmail(signInDto.getEmail());

        if (Objects.isNull(user)) {
            throw new AuthenticationFailException("user is not valid");
        }

        // hash the password

        try {
            if (!user.getPasswoprd().equals(hashPassword(signInDto.getPassword()))) {
                System.out.println("user already present");
                throw new AuthenticationFailException("wrong password");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // compare the password in DB

        // if password match

        AuthenticationToken token = authenticationService.getToken(user);
        // retrieve the token

        if (Objects.isNull(token)) {
            System.out.println("-----> user already present");
            throw new CustomException("token is not present");
        }

        System.out.println("-----> success");

        return new SignInReponseDto("success", token.getToken());
        // return response
    }
}
