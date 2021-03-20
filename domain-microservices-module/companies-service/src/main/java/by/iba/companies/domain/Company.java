package by.iba.companies.domain;

import by.iba.common.domain.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "companies")
public class Company extends AbstractEntity {


    private Long ownerId;

    @Id
    @Column(name = "unp", nullable = false, unique = true)
    private String UNP;//Payer account number

    private String email;

    private String site;

    private String title;

    private String description;

    private String address;

    private String phoneNumber;

}
