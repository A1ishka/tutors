package com.makogon.tutor.repository;

import com.makogon.tutor.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findAll();
    Student findByStudentId(long studentId);

    Student findByUserUserId(long userId);
}
