package by.iba.cargo.domain;

import by.iba.common.domain.CommonAttributes;
import by.iba.common.domain.TruckBodyType;

import java.util.Set;

public class Cargo extends CommonAttributes {

    private CargoType cargoType;

    private CargoDimensions cargoDimensions;

    private String description;

    private CargoPayment cargoPayment;

    private Set<TruckBodyType> truckBodyTypes; //типы кузова


}
