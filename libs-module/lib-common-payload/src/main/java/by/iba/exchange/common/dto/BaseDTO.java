package by.iba.exchange.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public abstract class BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer version;

    private LocalDateTime dateOfCreation;

    private LocalDateTime dateOfLastUpdate;

    @PrePersist
    private void abstractEntityPreInit() {
        this.dateOfCreation = LocalDateTime.now();
        this.dateOfLastUpdate = LocalDateTime.now();
        this.version = 1;
    }

    @PreUpdate
    private void abstractEntityPreUpdate() {
        this.dateOfLastUpdate = LocalDateTime.now();
        this.version++;
    }

}