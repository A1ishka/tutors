package com.makogon.tutor.repository;

import com.makogon.tutor.model.Duration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DurationRepository extends JpaRepository<Duration, Long> {
    List<Duration> findAll();
    Duration findByDurationId(long durationId);
    Duration findByType(String type);
}