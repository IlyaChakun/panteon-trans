package by.iba.common.controller;

import by.iba.common.dto.CountryResp;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/countries")
@CrossOrigin(origins = "*")
public interface CountryController extends BaseController<CountryResp> {

}
