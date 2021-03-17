package by.iba.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName;

    @Column(name = "display_name", nullable = false, unique = true)
    private String displayName;

    @Column(name = "is_displayed", nullable = false)
    private Boolean isDisplayed;

}
