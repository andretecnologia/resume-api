package com.system.io.repository;

import com.system.io.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    Resume findByResumeId(String id);

    void deleteResumeByResumeId(String id);

}
