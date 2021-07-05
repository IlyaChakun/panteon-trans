package by.iba.exchange.request.entity;

import by.iba.common.domain.BaseAbstractEntity;
import by.iba.exchange.common.domain.LoadingPayload;
import by.iba.exchange.common.domain.UnloadingPayload;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "transportation_requests")
@NoArgsConstructor
@Getter
@Setter
public class TransportationRequest extends BaseAbstractEntity {

//https://www.trakto.su/library/obrazes-zayavka-perevozki-gruza/zaru-min.png


    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();

    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;  //создать новый класс там хранить данные заказчика

    @OneToOne(cascade = CascadeType.ALL)
    private Carrier carrier; // данные перевозчика

    //  private Driver driverData; //создать новйы класс в котором хранятся данные водителя (пока туда вставить

    @OneToOne(cascade = CascadeType.ALL)
    private LoadingPayload loadingPayload;

    @OneToOne(cascade = CascadeType.ALL)
    private UnloadingPayload unloadingPayload;

    @Column(name = "cargo_offer_id")
    private Long cargoOfferId;

    @Column(name = "truck_offer_id")
    private Long truckOfferId;

    @Column(name = "freight_cost")
    private Double freightCost;//стоимость фрахта

    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "status")
    private TransportationRequestStatus status;

}
