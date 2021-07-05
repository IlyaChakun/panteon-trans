package by.iba.client;

import by.iba.common.dto.CountryResp;

public interface CommonClient {

    CountryResp getCountryById(Long id);

    Boolean existCountryById(Long id);

}
