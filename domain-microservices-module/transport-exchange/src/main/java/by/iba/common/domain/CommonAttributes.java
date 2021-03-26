package by.iba.common.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;

public abstract class CommonAttributes extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    private LoadingLocation loadingLocation;

    private UnloadingLocation unloadingLocation;

    private Set<CargoStowageMethod> cargoStowageMethods; // способ загрузки


}
