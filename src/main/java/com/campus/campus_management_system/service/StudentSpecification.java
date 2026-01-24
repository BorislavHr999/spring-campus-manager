package com.campus.campus_management_system.service;

import com.campus.campus_management_system.model.entity.Student;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecification {

    public static Specification<Student> getStudentsByCriteria(String name, String city, String clubName) {
        return (root, query, criteriaBuilder) -> {

            var predicate = criteriaBuilder.conjunction();

            if (name != null && !name.isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(root.get("firstName"), name));
            }

            if (city != null && !city.isEmpty()) {
                Join<Object, Object> addressJoin = root.join("address");
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(addressJoin.get("city"), city));
            }

            if (clubName != null && !clubName.isEmpty()) {
                Join<Object, Object> clubsJoin = root.join("clubs");
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(clubsJoin.get("name"), clubName));
            }

            return predicate;
        };
    }
}
