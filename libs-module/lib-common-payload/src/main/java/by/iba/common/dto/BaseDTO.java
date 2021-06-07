package by.iba.common.dto;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;

public abstract class BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int version;

    @PrePersist
    public void setVersion() {
        version = 1;
    }

    @PreUpdate
    public void updateVersion() {
        version++;
    }
}
