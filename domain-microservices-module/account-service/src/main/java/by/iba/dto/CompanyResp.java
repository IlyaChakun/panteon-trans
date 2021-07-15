package by.iba.dto;

import by.iba.common.dto.AddressDTO;
import by.iba.common.dto.core.BaseResp;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CompanyResp extends BaseResp {

    private Long companyId;

    private String unp;

    private Long ownerId;

    private String email;

    private String site;

    private String title;

    private String description;

    private AddressDTO address;

    private List<String> phoneNumbers = new ArrayList<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate foundationDate;

//    private BusinessType businessType;
//
//    private Set<CompanyFeatureResp> companyFeatures = new HashSet<>();
}
