package by.iba.client.impl;

import by.iba.client.CommonClient;
import by.iba.common.dto.CountryResp;
import by.iba.common.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommonClientImpl implements CommonClient {

    private final CountryService countryService;

    @Override
    public CountryResp getCountryById(Long id) {
        return countryService.getOne(id);
    }

    @Override
    public Boolean existCountryById(Long id) {
        return countryService.existById(id);
    }
}
