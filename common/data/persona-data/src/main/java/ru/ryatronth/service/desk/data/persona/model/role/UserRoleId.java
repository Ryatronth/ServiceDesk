package ru.ryatronth.service.desk.data.persona.model.role;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;
import lombok.Data;
import ru.ryatronth.service.desk.data.persona.model.role.constant.SystemRole;

@Data
@Embeddable
public class UserRoleId implements Serializable {

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "role", nullable = false)
    private SystemRole role;

}
