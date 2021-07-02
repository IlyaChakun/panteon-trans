package by.iba.common.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "version")
    private int version;

    @Column(name = "date_of_creation")
    private LocalDateTime dateOfCreation;

    @Column(name = "date_of_last_update")
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
