package com.makogon.tutor.service;

import com.makogon.tutor.model.Class;
import com.makogon.tutor.model.Tutor;
import com.makogon.tutor.repository.ClassRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("ClassService")
@Slf4j
@RequiredArgsConstructor
public class ClassService {

    private final ClassRepository classRepository;

    public List<Class> getClasses() {
        return classRepository.findAll();
    }

    public void saveClass(Class class8) {
        classRepository.save(class8);
    }

    public void deleteClass(long classId) {
        classRepository.deleteById(classId);
    }

    public Class getClass(long classId) throws ChangeSetPersister.NotFoundException {
        return classRepository.findByClassId(classId);
    }

    public void editClass(long classId, Class class8) {
        class8.setClassId(classId);
        classRepository.save(class8);
    }

    public List<Class> getClassesForTutor(Tutor tutor) {
        return classRepository.findAllByTutor(tutor);
    }
}