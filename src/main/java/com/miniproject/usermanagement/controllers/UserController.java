package com.miniproject.usermanagement.controllers;

import com.miniproject.usermanagement.exceptions.ForbidenActionException;
import com.miniproject.usermanagement.exceptions.InvalidCountUserException;
import com.miniproject.usermanagement.exceptions.InvalidUserNameOrEmailException;

import com.miniproject.usermanagement.models.User;
import com.miniproject.usermanagement.service.UserGeneratorService;
import com.miniproject.usermanagement.service.UserManagementService;
import com.miniproject.usermanagement.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserGeneratorService userGeneratorService;
    @Autowired
    private UserManagementService userManagementService;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/generate")
    public ResponseEntity<byte[]> genrateRandomUser(@RequestParam("count") Integer count) throws InvalidCountUserException {
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=users.json")
                .contentType(MediaType.APPLICATION_JSON)
                .body(userGeneratorService.generateUsers(count));
    }

    @PostMapping("/bash")
    public HashMap<String, Integer> uploadJsonFileUsers(@RequestParam("file") MultipartFile file) {
        return userManagementService.insertUsers(file);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public User getUsers(@RequestHeader(name = "Authorization") String token,
                         @PathVariable("username") String username) throws ForbidenActionException {

        String subject = jwtUtil.extractUsername(token.replaceAll("Bearer ", ""));

        if (username.equals("me")) {

            return userManagementService.findByNameOrEmail(subject);
        } else {
            User user = userManagementService.findByNameOrEmail(subject);
            if (user != null) {
                if (user.getRole().equals("admin")) {
                    return userManagementService.findByNameOrEmail(username);
                } else {
                    throw new ForbidenActionException("Action Non Permise");

                }
            } else {
                throw new InvalidUserNameOrEmailException("Email/Username Does Not Exist in db");
            }
        }
    }

}
