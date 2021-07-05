package by.iba.company.companies.dto.mapper;

import by.iba.common.mapper.core.SimpleAbstractMapper;
import by.iba.company.companies.domain.CompanyFeature;
import by.iba.company.companies.dto.CompanyFeatureResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyFeatureMapper extends SimpleAbstractMapper<CompanyFeature, CompanyFeatureResp> {

    @Autowired
    public CompanyFeatureMapper() {
        super(CompanyFeature.class, CompanyFeatureResp.class);
    }

}
