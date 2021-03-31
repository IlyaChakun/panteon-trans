package by.iba.common.dto;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class CommonAttributesDTO extends AbstractDTO {


    private Long id;

//    @OneToMany(cascade = CascadeType.ALL)
//    private LoadingLocation loadingLocation;
//
//    @OneToMany(cascade = CascadeType.ALL)
//    private UnloadingLocation unloadingLocation;

//    @ManyToMany(cascade = CascadeType.ALL)
//    private Set<CargoStowageMethod> cargoStowageMethods= new HashSet<>(); // способ загрузки

}
