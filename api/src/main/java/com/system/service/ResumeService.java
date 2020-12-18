package com.system.service;

import com.system.shared.dto.ResumeDTO;

import java.util.List;

public interface ResumeService {

    ResumeDTO addResume(ResumeDTO resumeDTO, String userId);

    List<ResumeDTO> getAccountResumes(String id);

    ResumeDTO getResumeById(String resumeId);

    ResumeDTO updateResume(ResumeDTO resumeDTO, String resumeId);

    List<ResumeDTO> deleteResumeById(String userId, String resumeId);
}
