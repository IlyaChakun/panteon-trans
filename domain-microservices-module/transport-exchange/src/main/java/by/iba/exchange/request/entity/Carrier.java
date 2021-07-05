package by.iba.exchange.request.entity;

import by.iba.common.domain.BaseAbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "carriers")
@Getter
@Setter
@NoArgsConstructor
public class Carrier extends BaseAbstractEntity {

    @Column(name = "unp",unique = false)
    private String UNP;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "country_id", nullable = false)
    private Long countryId;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "contact_name", nullable = false)
    private String contactName;

}
