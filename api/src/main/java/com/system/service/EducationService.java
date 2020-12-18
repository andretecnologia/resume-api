package com.system.service;

import com.system.shared.dto.EducationDTO;

import java.util.List;

public interface EducationService {

    EducationDTO addEducation(EducationDTO educationDTO, String resumeId);

    List<EducationDTO> getEducationsForResume(String resumeId);

    List<EducationDTO> getEducationsForAccountResumes(String id);

    EducationDTO getEducationById(String educationId);

    EducationDTO updateEducation(EducationDTO educationDTO, String educationId);


    List<EducationDTO> deleteEducationById(String resumeId, String educationId);

}
