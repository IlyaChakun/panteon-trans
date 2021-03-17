package by.iba.controller;

import by.iba.dto.AccountDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/accounts")
@CrossOrigin(origins = "*")
public interface AccountController {

    @PostMapping
     ResponseEntity<AccountDTO> createNewAccount(@Valid @RequestBody AccountDTO accountDTO);

}
