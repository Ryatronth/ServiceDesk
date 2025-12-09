package ru.ryatronth.service.desk.data.branch.model.branch;

import jakarta.persistence.metamodel.SingularAttribute;

import org.springframework.data.jpa.domain.Specification;

public class BranchSpecificationBuilder {

    private Specification<Branch> spec = Specification.unrestricted();

    public BranchSpecificationBuilder name(String name) {
        if (name != null && !name.isBlank()) {
            spec = spec.and(likeIgnoreCase(Branch_.name, name));
        }
        return this;
    }

    public BranchSpecificationBuilder area(String area) {
        if (area != null && !area.isBlank()) {
            spec = spec.and(likeIgnoreCase(Branch_.area, area));
        }
        return this;
    }

    public BranchSpecificationBuilder address(String address) {
        if (address != null && !address.isBlank()) {
            spec = spec.and(likeIgnoreCase(Branch_.address, address));
        }
        return this;
    }

    public Specification<Branch> build() {
        return spec;
    }

    private static Specification<Branch> likeIgnoreCase(SingularAttribute<Branch, String> field, String value) {
        return (root, query, cb) -> cb.like(cb.lower(root.get(field)), "%" + value.toLowerCase() + "%");
    }

    private static <T> Specification<Branch> equal(SingularAttribute<Branch, T> field, T value) {
        return (root, query, cb) -> cb.equal(root.get(field), value);
    }

}
