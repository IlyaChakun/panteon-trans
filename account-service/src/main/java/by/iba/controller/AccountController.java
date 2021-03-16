package by.iba.controller;

import by.iba.domain.User;
import by.iba.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@AllArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/")
    public User createNewAccount(@Valid @RequestBody User user) {
        log.info("in create new acc ");
        return accountService.create(user);
    }

    @GetMapping("/get")
    public String get() {
        return "hello get";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/test")
    public String getTest() {
        return "hello bro";
    }

}
