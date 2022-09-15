package com.miniproject.usermanagement.service;

import com.miniproject.usermanagement.models.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

public interface UserManagementService {

    HashMap<String, Integer> insertUsers(MultipartFile file);
    User findByNameOrEmail(String name);
}
