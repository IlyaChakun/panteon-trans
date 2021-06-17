package by.iba.common.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@MappedSuperclass
@Getter
@Setter
public abstract class CommonAttributes extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private LoadingLocation loadingLocation;

    @ManyToOne(cascade = CascadeType.ALL)
    private UnLoadingLocation unloadingLocation;

    @ManyToMany
    private Set<CargoStowageMethod> cargoStowageMethods= new HashSet<>(); // способ загрузки

}
