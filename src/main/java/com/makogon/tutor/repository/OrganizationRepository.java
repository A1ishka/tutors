package com.makogon.tutor.repository;

import com.makogon.tutor.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    List<Organization> findAll();

    Organization findByOrganizationId(long organizationId);
}