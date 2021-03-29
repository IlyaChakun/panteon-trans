package by.iba.companies.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "phone_numbers")
@Getter
@Setter
@NoArgsConstructor
public class PhoneNumber {

    @Id
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

}
