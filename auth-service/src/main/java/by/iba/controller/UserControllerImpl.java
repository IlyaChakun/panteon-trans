package by.iba.controller;


import by.iba.domain.User;
import by.iba.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import java.security.Principal;


@Slf4j
@AllArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public Principal getUser(Principal principal) {
        return principal;
    }

    @Override
    public void createUser(@Valid User user) {
        System.out.println("in create user ");
        userService.create(user);
    }

}
