package ru.ryatronth.service.desk.data.persona.model.user;

import jakarta.persistence.metamodel.SingularAttribute;

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

    public UserSpecificationBuilder patronymic(String patronymic) {
        if (patronymic != null && !patronymic.isBlank()) {
            spec = spec.and(likeIgnoreCase(User_.patronymic, patronymic));
        }
        return this;
    }

    public UserSpecificationBuilder phone(String phone) {
        if (phone != null && !phone.isBlank()) {
            spec = spec.and(likeIgnoreCase(User_.phone, phone));
        }
        return this;
    }

    public UserSpecificationBuilder workplace(String workplace) {
        if (workplace != null && !workplace.isBlank()) {
            spec = spec.and(equal(User_.workplace, workplace));
        }
        return this;
    }

    public UserSpecificationBuilder email(String email) {
        if (email != null && !email.isBlank()) {
            spec = spec.and(equal(User_.email, email));
        }
        return this;
    }

    public UserSpecificationBuilder branch(String branch) {
        if (branch != null) {
            spec = spec.and(equal(User_.branch, branch));
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

