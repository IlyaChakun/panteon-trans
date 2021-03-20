package by.iba.controller;

import by.iba.dto.AccountDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

@RequestMapping("/accounts")
@CrossOrigin(origins = "*")
public interface AccountController {

    @PostMapping
     ResponseEntity<AccountDTO> createNewAccount(@Valid @RequestBody AccountDTO accountDTO);

    @GetMapping("/{id}")
    ResponseEntity<AccountDTO> findById(@PathVariable("id") String accountId);

}
