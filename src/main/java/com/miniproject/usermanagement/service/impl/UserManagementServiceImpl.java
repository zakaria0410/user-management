package com.miniproject.usermanagement.service.impl;

import com.miniproject.usermanagement.Repository.UserRepository;
import com.miniproject.usermanagement.models.User;
import com.miniproject.usermanagement.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserManagementServiceImpl implements UserManagementService {
    @Autowired
    private JsonReaderFileJsonUserServiceImpl jsonReaderFileJsonUserService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public HashMap<String, Integer> insertUsers(MultipartFile file) {
        List<User> users = jsonReaderFileJsonUserService.readUsers(file);
        HashMap<String, Integer> integerHashMap = new HashMap<>();
        String success = "Nombre d'enregistrements importés avec succés";
        String failure = "Nombre non importés";
        integerHashMap.put(success, 0);
        integerHashMap.put(failure, 0);
        List<User> usersWithPasswordEncrypted=users.stream().map(user -> {
            User userWithPasswordEncrypted=user;
            userWithPasswordEncrypted.setPassword(passwordEncoder.encode(user.getPassword()));
            return userWithPasswordEncrypted;
        }).collect(Collectors.toList());
        usersWithPasswordEncrypted.stream().forEach(user -> {
            Optional<User> userOptional = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail());
            if (userOptional.isPresent()) {
                Integer integer = integerHashMap.get(failure) + 1;
                integerHashMap.put(failure, integer);
            } else {
                userRepository.save(user);
                Integer integer = integerHashMap.get(success) + 1;
                integerHashMap.put(success, integer);
            }
        });
        return integerHashMap;
    }

    @Override
    public User findByNameOrEmail(String emailOrPassword) {
        return userRepository.findByUsernameOrEmail(emailOrPassword,emailOrPassword).get();
    }
}
