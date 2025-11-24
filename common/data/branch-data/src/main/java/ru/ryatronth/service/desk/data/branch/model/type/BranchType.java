package ru.ryatronth.service.desk.data.branch.model.type;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ryatronth.service.desk.data.branch.model.branch.Branch;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "branch_type")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchType {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Branch> branches = new HashSet<>();

}
