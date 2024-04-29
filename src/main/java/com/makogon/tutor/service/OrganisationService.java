package com.makogon.tutor.service;

import com.makogon.tutor.model.Organization;
import com.makogon.tutor.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("OrganisationService")
@Slf4j
@RequiredArgsConstructor
public class OrganisationService {

    private final OrganizationRepository organizationRepository;

    public List<Organization> getOrganizations() {
        return organizationRepository.findAll();
    }

    public void saveOrganization(Organization organization) {
        organizationRepository.save(organization);
    }

    public void deleteOrganization(long organizationId) {
        organizationRepository.deleteById(organizationId);
    }

    public Organization getOrganization(long organizationId) throws ChangeSetPersister.NotFoundException {
        return organizationRepository.findByOrganizationId(organizationId);
    }

    public void editOrganization(long organizationId, Organization organization) {
        organization.setOrganizationId(organizationId);
        organizationRepository.save(organization);
    }
}