package com.miniproject.usermanagement.controllers;

import com.miniproject.usermanagement.exceptions.InvalidUserNameOrEmailException;
import com.miniproject.usermanagement.models.AuthRequest;
import com.miniproject.usermanagement.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;



    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        return jwtUtil.generateToken(authRequest.getUsername());
    }


}
