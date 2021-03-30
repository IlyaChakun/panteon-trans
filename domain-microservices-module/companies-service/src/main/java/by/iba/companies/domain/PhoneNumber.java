package by.iba.companies.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "phone_numbers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumber {

    @Id
    @Column(name = "value", nullable = false, unique = true)
    private String value;

//    @ManyToOne
//    @JoinColumn(name = "company_id")
//    private Company company;

}
