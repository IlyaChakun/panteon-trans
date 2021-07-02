package by.iba.request.entity;

import by.iba.common.domain.BaseAbstractEntity;
import by.iba.common.domain.LoadingLocation;
import by.iba.common.domain.UnLoadingLocation;

import java.util.UUID;

public class TransportationRequest extends BaseAbstractEntity {

//https://www.trakto.su/library/obrazes-zayavka-perevozki-gruza/zaru-min.png

    private String uuid = UUID.randomUUID().toString();
    // private Customer customer;  //создать новый класс там хранить данные заказчика

    // private Carrier carrier; // данные перевозчика
    //  private Driver driverData; //создать новйы класс в котором хранятся данные водителя (пока туда вставить

    private LoadingLocation loadingLocation;

    private UnLoadingLocation unLoadingLocation;

    private Long cargoOfferId;

    private Long truckOfferId;

    private Double freightCost;//стоимость фрахта

    private String additionalInfo;

    private TransportationRequestStatus status;

}