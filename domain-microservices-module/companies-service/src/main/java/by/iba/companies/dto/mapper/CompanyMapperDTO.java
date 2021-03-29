package by.iba.companies.dto.mapper;

import by.iba.common.dto.mapper.AbstractMapperDTO;
import by.iba.companies.domain.Company;
import by.iba.companies.dto.CompanyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapperDTO extends AbstractMapperDTO<Company, CompanyDTO> {

    @Autowired
    public CompanyMapperDTO() {
        super(Company.class, CompanyDTO.class);
    }

}
