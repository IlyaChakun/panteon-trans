package by.iba.common.entity;

import by.iba.common.domain.core.FullAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "regions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Region extends FullAbstractEntity {

    @Column(name = "region_name", nullable = false, length = 48)
    private String regionName;

}
