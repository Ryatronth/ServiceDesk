package ru.ryatronth.service.desk.data.persona.model.user;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ryatronth.service.desk.data.persona.model.converter.StringListConverter;

@Table(name = "users")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "workplace", nullable = false)
    private String workplace;

    @Column(name = "branch")
    private String branch;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Convert(converter = StringListConverter.class)
    @Column(name = "roles", columnDefinition = "TEXT")
    private List<String> roles;

}
