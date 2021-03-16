package by.iba.controller;

import by.iba.domain.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public interface UserController {

    @GetMapping("/current")
    Principal getUser(Principal principal);

    @PreAuthorize("#oauth2.hasScope('server')")
    @PostMapping()
    void createUser(@Valid @RequestBody User user);

}