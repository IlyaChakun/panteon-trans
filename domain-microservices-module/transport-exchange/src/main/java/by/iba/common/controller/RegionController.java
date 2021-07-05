package by.iba.common.controller;

import by.iba.common.dto.RegionResp;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/regions")
@CrossOrigin(origins = "*")
public interface RegionController extends BaseController<RegionResp> {

}
