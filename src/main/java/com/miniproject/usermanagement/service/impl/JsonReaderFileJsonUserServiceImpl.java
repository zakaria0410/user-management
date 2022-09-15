package com.miniproject.usermanagement.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miniproject.usermanagement.models.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class JsonReaderFileJsonUserServiceImpl {
    public List<User> readUsers(MultipartFile file) {
        ObjectMapper mapper = new ObjectMapper();

        // convert JSON array to list of users
        List<User> users = null;
        try {
            users = Arrays.asList(mapper.readValue(file.getInputStream(), User[].class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return users;

    }
}
