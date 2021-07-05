package by.iba.common.controller;

import by.iba.common.dto.CountryResp;
import by.iba.common.service.CountryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class CountryControllerImpl implements CountryController {

    private final CountryService countryService;

    @Override
    public ResponseEntity<CountryResp> findById(String id) {
        CountryResp countryDTO = countryService.getOne(Long.valueOf(id));

        return ResponseEntity.ok(countryDTO);
    }

    @Override
    public ResponseEntity<List<CountryResp>> findAll() {
        List<CountryResp> countries = countryService.findAll();

        return ResponseEntity.ok(countries);
    }

    @Override
    public ResponseEntity<Boolean> existById(String id) {

        Boolean isExist = countryService.existById(Long.valueOf(id));

        return ResponseEntity.ok(isExist);
    }

}
