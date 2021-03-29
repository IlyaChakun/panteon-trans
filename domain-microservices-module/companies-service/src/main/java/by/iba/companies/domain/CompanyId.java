package by.iba.companies.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyId implements Serializable {

    private String UNP;

    private Long ownerId;

}
