//package by.iba.controller;
//
//import by.iba.dto.CountryDTO;
//import by.iba.service.CountryService;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController //TODO FIX SERVICE
//@AllArgsConstructor
//@Slf4j
//public class CountryControllerImpl implements CountryController {
//
//    private final CountryService countryService;
//
//    @Override
//    public ResponseEntity<CountryDTO> findById(String id) {
//        CountryDTO countryDTO = countryService.getOne(Long.valueOf(id));
//
//        return ResponseEntity.ok(countryDTO);
//    }
//
//    @Override
//    public ResponseEntity<List<CountryDTO>> findAll() {
//        List<CountryDTO> countries = countryService.findAll();
//
//        return ResponseEntity.ok(countries);
//    }
//
//    @Override
//    public ResponseEntity<Boolean> existById(String id) {
//
//        Boolean isExist = countryService.existById(Long.valueOf(id));
//
//        return ResponseEntity.ok(isExist);
//    }
//
//}
