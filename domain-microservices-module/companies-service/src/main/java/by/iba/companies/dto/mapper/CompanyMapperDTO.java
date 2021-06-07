package by.iba.companies.dto.mapper;

import by.iba.common.dto.mapper.AbstractMapperDTO;
import by.iba.companies.domain.Company;
import by.iba.companies.dto.CompanyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Objects;

@Component
public class CompanyMapperDTO extends AbstractMapperDTO<Company, CompanyDTO> {


    @Autowired
    public CompanyMapperDTO() {
        super(Company.class, CompanyDTO.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Company.class, CompanyDTO.class)
                .addMappings(m -> m.skip(CompanyDTO::setPhoneNumbers)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(CompanyDTO.class, Company.class)
                .addMappings(m -> m.skip(Company::setPhoneNumbers)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(final CompanyDTO source, final Company destination) {
        source.getPhoneNumbers()
                .forEach(phoneNumber -> {
                    destination.getPhoneNumbers().add(phoneNumber);
                });
    }

    @Override
    protected void mapSpecificFields(final Company source, final CompanyDTO destination) {
        destination.setPhoneNumbers(Objects.isNull(source) || Objects.isNull(source.getCompanyId())
                ? null : new ArrayList<>(source.getPhoneNumbers()));
    }


}
