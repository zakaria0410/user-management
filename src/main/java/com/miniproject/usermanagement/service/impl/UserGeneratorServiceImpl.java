package com.miniproject.usermanagement.service.impl;

import com.github.javafaker.Faker;
import com.miniproject.usermanagement.exceptions.InvalidCountUserException;
import com.miniproject.usermanagement.models.User;
import com.miniproject.usermanagement.service.Utility.JsonExporterService;
import com.miniproject.usermanagement.service.UserGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class UserGeneratorServiceImpl implements UserGeneratorService {

    @Autowired
    private JsonExporterService<User> userJsonExporterService;

    @Override
    public byte[] generateUsers(Integer count) throws InvalidCountUserException {
        if(count>0) {
            List<User> users = IntStream
                    .range(0, count)
                    .mapToObj(index -> fakerToUser())
                    .collect(Collectors.toList());
            String customerJsonString = userJsonExporterService.export(users);

            byte[] usersJsonBytes = customerJsonString.getBytes();
            return usersJsonBytes;
        }else{
            throw new InvalidCountUserException("Count Must Be Strictly Positif Number");
        }
    }

    @Override
    public User fakerToUser() {
        String[] roles = {"admin", "user"};
        Random ran = new Random();
        Faker faker = new Faker();
        User user=new User();
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(faker.internet().emailAddress());
        user.setBirthDate(faker.date().birthday());
        user.setCity(faker.address().city());
        user.setCountry(faker.address().countryCode());
        user.setAvatar(faker.avatar().image());
        user.setCompany(faker.company().name());
        user.setJobPosition(faker.job().position());
        user.setMobile(faker.phoneNumber().cellPhone());
        user.setUsername(faker.name().username());
        user.setPassword(faker.internet().password(6,10));
        user.setRole(roles[ran.nextInt(roles.length)]);
        return user;
    }
}
