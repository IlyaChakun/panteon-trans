package by.iba.common.controller;

import by.iba.common.dto.RegionResp;
import by.iba.common.service.RegionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class RegionControllerImpl implements RegionController {

    private final RegionService regionService;

    @Override
    public ResponseEntity<RegionResp> findById(String id) {
        RegionResp regionDTO = regionService.getOne(Long.valueOf(id));

        return ResponseEntity.ok(regionDTO);
    }

    @Override
    public ResponseEntity<List<RegionResp>> findAll() {
        List<RegionResp> regions = regionService.findAll();

        return ResponseEntity.ok(regions);
    }

    @Override
    public ResponseEntity<Boolean> existById(String id) {
        Boolean isExist = regionService.existById(Long.valueOf(id));

        return ResponseEntity.ok(isExist);
    }


}
