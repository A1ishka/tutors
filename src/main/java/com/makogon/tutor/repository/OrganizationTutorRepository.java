package com.makogon.tutor.repository;

import com.makogon.tutor.model.Organization;
import com.makogon.tutor.model.OrganizationTutor;
import com.makogon.tutor.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationTutorRepository extends JpaRepository<OrganizationTutor, Long> {
    List<OrganizationTutor> findAll();

    OrganizationTutor findByOrganizationTutorId(long organizationTutorId);

    List<OrganizationTutor> findAllByTutor(Tutor tutor);

    List<OrganizationTutor> findAllByOrganization(Organization organization);
}