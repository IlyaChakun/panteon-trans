package by.iba.controller;

import by.iba.dto.ApiResponse;
import by.iba.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;


@RequestMapping("/users")
@CrossOrigin(origins = "*")
public interface UserController {

    @GetMapping("/current")
    Principal getUser(Principal principal);

    @PreAuthorize("#oauth2.hasScope('server')")
    @PostMapping()
    ResponseEntity<UserDTO> save(@Valid @RequestBody UserDTO user);

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    ResponseEntity<ApiResponse> confirmUserAccount(@RequestParam("token") String confirmationToken);

}
