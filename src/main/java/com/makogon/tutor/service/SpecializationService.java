package com.makogon.tutor.service;

import com.makogon.tutor.model.Specialization;
import com.makogon.tutor.repository.SpecializationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SpecializationService")
@Slf4j
@RequiredArgsConstructor
public class SpecializationService {

    private final SpecializationRepository specializationRepository;

    public List<Specialization> getSpecializations() {
        return specializationRepository.findAll();
    }

    public void saveSpecialization(Specialization specialization) {
        specializationRepository.save(specialization);
    }

    public void deleteSpecialization(long specializationId) {
        specializationRepository.deleteById(specializationId);
    }

    public Specialization getSpecialization(long specializationId) throws ChangeSetPersister.NotFoundException {
        return specializationRepository.findBySpecializationId(specializationId);
    }

    public void editSpecialization(long specializationId, Specialization specialization) {
        specialization.setSpecializationId(specializationId);
        specializationRepository.save(specialization);
    }
}