package by.iba.common.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Dimensions {

    @Column(name = "volume")
    private Double volume;

    @Column(name = "length")
    private Double length;

    @Column(name = "height")
    private Double height;

}
