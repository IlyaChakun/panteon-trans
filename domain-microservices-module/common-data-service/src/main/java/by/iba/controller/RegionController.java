package by.iba.controller;

import by.iba.dto.RegionDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/regions")
@CrossOrigin(origins = "*")
public interface RegionController extends BaseController<RegionDTO> {

}
