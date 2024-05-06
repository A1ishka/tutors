package com.makogon.tutor.service;

import com.makogon.tutor.model.Manager;
import com.makogon.tutor.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("ManagerService")
@Slf4j
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;

    public List<Manager> getManager() {
        return managerRepository.findAll();
    }

    public void saveManager(Manager managerId) {
        managerRepository.save(managerId);
    }

    public void deleteManager(long managerId) {
        managerRepository.deleteById(managerId);
    }

    public Manager getManager(long managerId) throws ChangeSetPersister.NotFoundException {
        return managerRepository.findByManagerId(managerId);
    }

    public void editManager(long managerId, Manager manager) {
        manager.setManagerId(managerId);
        managerRepository.save(manager);
    }

    public Manager findByUserUserId(long userId) {
        return managerRepository.findByUserUserId(userId);
    }
}
