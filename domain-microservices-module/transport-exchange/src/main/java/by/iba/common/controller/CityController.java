package by.iba.common.controller;

import by.iba.common.dto.CityResp;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/cities")
@CrossOrigin(origins = "*")
public interface CityController extends BaseController<CityResp> {

}
