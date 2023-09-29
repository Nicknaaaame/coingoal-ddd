package ru.lapotko.coingoal.application.rest.service.impl;

import org.springframework.stereotype.Service;
import ru.lapotko.coingoal.application.rest.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public String getCurrentUserId() {
        return "ADMIN";
    }
}
