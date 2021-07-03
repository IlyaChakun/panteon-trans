package by.iba.common.controller;

import by.iba.common.dto.CountryDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/countries")
@CrossOrigin(origins = "*")
public interface CountryController extends BaseController<CountryDTO> {

}
