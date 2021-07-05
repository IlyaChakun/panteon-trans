package by.iba.common.domain.core;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
@NoArgsConstructor
@Getter
public abstract class BaseEntity extends MappableObject {

    @Column(name = "version")
    private Integer version;

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
