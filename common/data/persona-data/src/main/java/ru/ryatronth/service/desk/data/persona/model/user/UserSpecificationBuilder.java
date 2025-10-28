package ru.ryatronth.service.desk.data.persona.model.user;

import jakarta.persistence.metamodel.SingularAttribute;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

public class UserSpecificationBuilder {

    private Specification<User> spec = Specification.unrestricted();

    public UserSpecificationBuilder firstName(String firstName) {
        if (firstName != null && !firstName.isBlank()) {
            spec = spec.and(likeIgnoreCase(User_.firstName, firstName));
        }
        return this;
    }

    public UserSpecificationBuilder lastName(String lastName) {
        if (lastName != null && !lastName.isBlank()) {
            spec = spec.and(likeIgnoreCase(User_.lastName, lastName));
        }
        return this;
    }

    public UserSpecificationBuilder address(String address) {
        if (address != null && !address.isBlank()) {
            spec = spec.and(likeIgnoreCase(User_.address, address));
        }
        return this;
    }

    public UserSpecificationBuilder workplace(String workplace) {
        if (workplace != null && !workplace.isBlank()) {
            spec = spec.and(likeIgnoreCase(User_.workplace, workplace));
        }
        return this;
    }

    public UserSpecificationBuilder branchId(UUID branchId) {
        if (branchId != null) {
            spec = spec.and(equal(User_.branchId, branchId));
        }
        return this;
    }

    public Specification<User> build() {
        return spec;
    }

    private static Specification<User> likeIgnoreCase(SingularAttribute<User, String> field, String value) {
        return (root, query, cb) -> cb.like(cb.lower(root.get(field)), "%" + value.toLowerCase() + "%");
    }

    private static <T> Specification<User> equal(SingularAttribute<User, T> field, T value) {
        return (root, query, cb) -> cb.equal(root.get(field), value);
    }
}

