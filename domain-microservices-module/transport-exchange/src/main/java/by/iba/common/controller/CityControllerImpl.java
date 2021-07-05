package by.iba.common.controller;

import by.iba.common.dto.CityResp;
import by.iba.common.service.CityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class CityControllerImpl implements CityController {

    private final CityService cityService;

    @Override
    public ResponseEntity<CityResp> findById(String id) {
        CityResp cityDTO = cityService.getOne(Long.valueOf(id));

        return ResponseEntity.ok(cityDTO);
    }

    @Override
    public ResponseEntity<List<CityResp>> findAll() {
        List<CityResp> cities = cityService.findAll();

        return ResponseEntity.ok(cities);
    }

    @Override
    public ResponseEntity<Boolean> existById(String id) {
        Boolean isExist = cityService.existById(Long.valueOf(id));

        return ResponseEntity.ok(isExist);
    }

}
