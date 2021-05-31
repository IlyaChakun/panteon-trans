package by.iba.common.dto;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.util.HashSet;
import java.util.Set;

@MappedSuperclass
@Getter
@Setter
public abstract class CommonAttributesDTO extends AbstractDTO {//TODO add validation


    private Long id;

    private LoadingLocationDTO loadingLocation;

    private UnloadingLocationDTO unloadingLocation;

    private Set<Long> cargoStowageMethodIds = new HashSet<>();

}
