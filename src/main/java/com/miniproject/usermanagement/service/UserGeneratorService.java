package com.miniproject.usermanagement.service;

import com.miniproject.usermanagement.exceptions.InvalidCountUserException;
import com.miniproject.usermanagement.models.User;

import java.util.List;

public interface UserGeneratorService {

    byte[] generateUsers(Integer count) throws InvalidCountUserException;
    User fakerToUser();
}
