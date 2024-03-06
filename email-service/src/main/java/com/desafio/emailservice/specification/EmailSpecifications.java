package com.desafio.emailservice.specification;

import com.desafio.emailservice.dto.EmailFilterDTO;
import com.desafio.emailservice.model.Email;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class EmailSpecifications {

    public static Specification<Email> generateDinamic(EmailFilterDTO filter) {
        Specification<Email> specification = Specification.where(null);

        if (filter.getCustomerId() != null) {
            specification = specification.and(customerIdEquals(filter.getCustomerId()));
        }

        if (filter.getAgency() != null) {
            specification = specification.and(agencyEquals(filter.getAgency()));
        }

        if (filter.getAccount() != null) {
            specification = specification.and(accountEquals(filter.getAccount()));
        }

        return specification;
    }

    private static Specification<Email> customerIdEquals(UUID customerId) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("customerId"), customerId);
    }

    private static Specification<Email> agencyEquals(String agency) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(criteriaBuilder.upper(root.get("agency")), agency.toUpperCase());
    }

    private static Specification<Email> accountEquals(Long account) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("account"), account);
    }
}
