package by.iba.companies.service;

import by.iba.common.dto.PageWrapper;
import by.iba.common.exception.ResourceNotFoundException;
import by.iba.companies.domain.Company;
import by.iba.companies.dto.CompanyDTO;
import by.iba.companies.dto.mapper.CompanyMapperDTO;
import by.iba.companies.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapperDTO companyMapper;

    @Override
    public CompanyDTO save(final CompanyDTO companyDTO) {
        log.info("Start saving the company with UNP = {}", companyDTO.getUNP());

        //TODO валидации


        companyRepository.save(companyMapper.toEntity(companyDTO));

        log.info("Finish saving the company with UNP = {}", companyDTO.getUNP());

        return companyDTO;
    }

    @Override
    public CompanyDTO update(final String unp, final CompanyDTO companyDTO) {
        return null;
    }

    @Override
    public void delete(final String unp) {
        log.info("Start deleting the company by UNP = {}", unp);

        final Company company = getCompanyByUNP(unp);

        companyRepository.delete(company);

        log.info("Company with unp = {} has been deleted!", unp);
    }

    @Override
    public CompanyDTO findByUNP(final String unp) {
        log.info("Start finding the company by UNP = {}", unp);

        final Company company = getCompanyByUNP(unp);

        log.info("Company with unp = {} has been found!", unp);

        return companyMapper.toDto(company);
    }

    private Company getCompanyByUNP(final String unp) {
        return companyRepository.findByUNP(unp)
                .orElseThrow(() -> new ResourceNotFoundException("exception.company.by_unp_not_found_exception"));
    }

    @Override
    public PageWrapper<CompanyDTO> findAll() {
        //TODO    Pageable pageable = commonServiceHelper.getPageable(page, size);
        //
        ////        Specification<Flower> specification = getSpecification(searchAndSortParamDto);
        ////        Page<Flower> flowers = flowerRepository.findAll(specification, pageable);
        //        Page<Product> products = productRepository.findAll(pageable);
        //
        //        return
        //                new PageWrapper<>(
        //                        productMapper.toDtoList(products.toList()),
        //                        products.getTotalPages(),
        //                        products.getTotalElements());

        log.info("Start finding all companies");

        final List<CompanyDTO> companyDtos = new ArrayList<>();
        companyRepository.findAll().forEach(company -> companyDtos.add(companyMapper.toDto(company)));

        final PageWrapper<CompanyDTO> pageWrapper = new PageWrapper<>(companyDtos, 0, 0);

        log.info("Finish finding all companies");

        return pageWrapper;
    }
}