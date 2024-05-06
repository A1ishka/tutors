package com.makogon.tutor.service;

import com.makogon.tutor.model.Organization;
import com.makogon.tutor.model.OrganizationTutor;
import com.makogon.tutor.model.SpecializationTutor;
import com.makogon.tutor.model.Tutor;
import com.makogon.tutor.repository.OrganizationTutorRepository;
import com.makogon.tutor.repository.SpecializationTutorRepository;
import com.makogon.tutor.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TutorService")
@Slf4j
@RequiredArgsConstructor
public class TutorService {

    private final TutorRepository tutorRepository;
    private final SpecializationTutorRepository specializationTutorRepository;
    private final OrganizationTutorRepository organizationTutorRepository;
    public List<Tutor> getTutors() {
        return tutorRepository.findAll();
    }
    public void saveTutor(Tutor tutor) {
        tutorRepository.save(tutor);
    }
    public void deleteTutor(long tutorId) {
        tutorRepository.deleteById(tutorId);
    }
    public Tutor getTutor(long tutorId) throws ChangeSetPersister.NotFoundException {
        return tutorRepository.findByTutorId(tutorId);
    }
    public void editTutor(long tutorId, Tutor tutor) {
        tutor.setTutorId(tutorId);
        tutorRepository.save(tutor);
    }

    public List<Tutor> searchTutors(String query) {
        // Используйте методы запросов JPA или создайте собственный метод запроса с помощью аннотации @Query
        return tutorRepository.findByFullNameContainingIgnoreCase(query);
    }

    //SpecializationTutor
    public List<SpecializationTutor> getSpecializationTutors() {
        return specializationTutorRepository.findAll();
    }
    public void saveSpecializationTutor(SpecializationTutor specializationTutor) {
        specializationTutorRepository.save(specializationTutor);
    }
    public void deleteSpecializationTutor(long specializationTutorId) {
        specializationTutorRepository.deleteById(specializationTutorId);
    }
    public SpecializationTutor getSpecializationTutor(long specializationTutorId) throws ChangeSetPersister.NotFoundException {
        return specializationTutorRepository.findBySpecializationTutorId(specializationTutorId);
    }

    public List<SpecializationTutor> findAllByTutor(Tutor tutor) throws ChangeSetPersister.NotFoundException {
        return specializationTutorRepository.findAllByTutor(tutor);
    }


    public void editSpecializationTutor(long specializationTutorId, SpecializationTutor specializationTutor) {
        specializationTutor.setSpecializationTutorId(specializationTutorId);
        specializationTutorRepository.save(specializationTutor);
    }

    //OrganizationTutor
    public List<OrganizationTutor> getOrganizationTutors() {
        return organizationTutorRepository.findAll();
    }
    public void saveOrganizationTutor(OrganizationTutor organizationTutor) {
        organizationTutorRepository.save(organizationTutor);
    }
    public void deleteOrganizationTutor(long organizationTutorId) {
        organizationTutorRepository.deleteById(organizationTutorId);
    }
    public OrganizationTutor getOrganizationTutor(long organizationTutorId) throws ChangeSetPersister.NotFoundException {
        return organizationTutorRepository.findByOrganizationTutorId(organizationTutorId);
    }
    public void editOrganizationTutor(long organizationTutorId, OrganizationTutor organizationTutor) {
        organizationTutor.setOrganizationTutorId(organizationTutorId);
        organizationTutorRepository.save(organizationTutor);
    }

    public Tutor findByUserUserId(long userId) {
        return tutorRepository.findByUserUserId(userId);
    }

    public List<OrganizationTutor> getOrganizationTutorsByOrganization(Organization organization) {
        return organizationTutorRepository.findAllByOrganization(organization);
    }
}