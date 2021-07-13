package by.iba.company.companies.dto.mapper;

import by.iba.client.CommonClient;
import by.iba.common.dto.core.FullAbstractResp;
import by.iba.common.mapper.core.FullAbstractMapper;
import by.iba.company.companies.domain.Company;
import by.iba.company.companies.dto.CompanyReq;
import by.iba.company.companies.dto.CompanyResp;
import by.iba.company.companies.repository.CompanyFeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CompanyMapper extends FullAbstractMapper<Company, CompanyResp, CompanyReq> {

    private final CompanyFeatureRepository companyFeatureRepository;
    private final CompanyFeatureMapper companyFeatureMapper;
    private final CommonClient commonClient;

    @Autowired
    public CompanyMapper(CompanyFeatureRepository companyFeatureRepository,
                         CompanyFeatureMapper companyFeatureMapper,
                         CommonClient commonClient) {
        super(Company.class, CompanyResp.class);
        this.companyFeatureRepository = companyFeatureRepository;
        this.companyFeatureMapper = companyFeatureMapper;
        this.commonClient = commonClient;
    }

    @PostConstruct
    public void setupMapper() {

        mapper.createTypeMap(Company.class, CompanyResp.class)
                .addMappings(m -> m.skip(CompanyResp::setPhoneNumbers))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(CompanyResp.class, Company.class)
                .addMappings(m -> m.skip(Company::setPhoneNumbers))
                .setPostConverter(toEntityConverter());

        mapper.createTypeMap(CompanyReq.class, Company.class)
                .addMappings(m -> m.skip(Company::setCompanyFeatures))
                .setPostConverter(toEntityFromReqConverter());
    }

    @Override
    protected void mapSpecificFields(final CompanyResp source, final Company destination) {
        destination.setCompanyFeatures(
                source.getCompanyFeatures()
                        .stream()
                        .map(FullAbstractResp::getId)
                        .map(companyFeatureRepository::getOne)
                        .collect(Collectors.toSet())
        );
    }

    @Override
    protected void mapSpecificFields(final Company source, final CompanyResp destination) {
        destination.setPhoneNumbers(
                Objects.isNull(source) || Objects.isNull(source.getCompanyId())
                        ? null : new ArrayList<>(source.getPhoneNumbers())
        );

        destination.setCompanyFeatures(
                source.getCompanyFeatures()
                        .stream()
                        .map(companyFeatureMapper::toDto)
                        .collect(Collectors.toSet())
        );
    }

    @Override
    protected void mapSpecificFields(final CompanyReq source, final Company destination) {
        // mapping
       destination.setCompanyFeatures(
                source.getCompanyFeatureIds()
                        .stream()
                        .map(companyFeatureRepository::getOne)
                        .collect(Collectors.toSet())
        );

    }
}
