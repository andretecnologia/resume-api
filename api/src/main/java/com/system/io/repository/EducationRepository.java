package com.system.io.repository;

import com.system.io.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Long> {

    List<Education> getAllByResume_ResumeId(String id);

    Education findByEducationId(String educationId);

    void deleteByEducationId(String educationId);

}
