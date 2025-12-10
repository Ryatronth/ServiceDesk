package ru.ryatronth.service.desk.data.ticket.model.category;

import jakarta.persistence.metamodel.SingularAttribute;

import org.springframework.data.jpa.domain.Specification;

public class TicketCategorySpecificationBuilder {

    private Specification<TicketCategory> spec = Specification.unrestricted();

    public TicketCategorySpecificationBuilder name(String name) {
        if (name != null && !name.isBlank()) {
            spec = spec.and(likeIgnoreCase(TicketCategory_.name, name));
        }
        return this;
    }

    public TicketCategorySpecificationBuilder description(String description) {
        if (description != null && !description.isBlank()) {
            spec = spec.and(likeIgnoreCase(TicketCategory_.description, description));
        }
        return this;
    }

    public Specification<TicketCategory> build() {
        return spec;
    }

    private static Specification<TicketCategory> likeIgnoreCase(SingularAttribute<TicketCategory, String> field,
                                                                String value) {
        return (root, query, cb) -> cb.like(cb.lower(root.get(field)), "%" + value.toLowerCase() + "%");
    }
}
