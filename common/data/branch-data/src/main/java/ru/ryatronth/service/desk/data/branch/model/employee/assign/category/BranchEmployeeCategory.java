package ru.ryatronth.service.desk.data.branch.model.employee.assign.category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "branch_employee_category")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchEmployeeCategory {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "employee_id", nullable = false)
    private UUID employeeId;

    @Column(name = "category_id", nullable = false)
    private UUID categoryId;

}
