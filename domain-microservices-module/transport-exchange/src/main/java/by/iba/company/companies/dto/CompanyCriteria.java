package by.iba.company.companies.dto;

import by.iba.common.dto.BaseSearchCriteria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CompanyCriteria extends BaseSearchCriteria {

    private Set<Long> companyFeatureIds = new HashSet<>();


}
