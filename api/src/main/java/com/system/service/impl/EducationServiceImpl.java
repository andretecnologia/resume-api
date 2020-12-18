package com.system.service.impl;


import com.system.exceptions.ResumeServiceException;
import com.system.exceptions.EducationServiceException;
import com.system.exceptions.AccountServiceException;
import com.system.io.entity.Account;
import com.system.io.entity.Resume;
import com.system.io.entity.Education;
import com.system.io.repository.ResumeRepository;
import com.system.io.repository.EducationRepository;
import com.system.io.repository.AccountRepository;
import com.system.service.EducationService;
import com.system.shared.Utils;
import com.system.shared.dto.AccountDTO;
import com.system.shared.dto.ResumeDTO;
import com.system.shared.dto.EducationDTO;
import com.system.shared.mapper.EducationMapper;
import com.system.shared.mapper.AccountMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;
    private final ResumeRepository resumeRepository;
    private final AccountRepository accountRepository;
    private final Utils utils;

    public EducationServiceImpl(EducationRepository educationRepository, ResumeRepository resumeRepository, AccountRepository accountRepository, Utils utils) {
        this.educationRepository = educationRepository;
        this.resumeRepository = resumeRepository;
        this.accountRepository = accountRepository;
        this.utils = utils;
    }

    @Override
    public List<EducationDTO> getEducationsForAccountResumes(String id) {

        Account account = accountRepository.findByAccountId(id);

        if (account == null) {
            throw new AccountServiceException("User with " + id + "doesn't exist!");
        }
        AccountDTO accountDTO = AccountMapper.INSTANCE.accountToAccountDto(account);

        List<EducationDTO> returnValue = new ArrayList<>();

        for (ResumeDTO resumeDTO : accountDTO.getResumes()) {
            returnValue.addAll(resumeDTO.getEducations());
        }

        return returnValue;
    }

    @Override
    public EducationDTO getEducationById(String educationId) {

        Education education = educationRepository.findByEducationId(educationId);

        if (education == null) {
            throw new EducationServiceException("Education with " + educationId + "doesn't exist!");
        }

        return EducationMapper.INSTANCE.educationToEducationDTO(education);
    }

    @Override
    public EducationDTO updateEducation(EducationDTO educationDTO, String educationId) {
        Education education = educationRepository.findByEducationId(educationId);

        if (education == null) {
            throw new EducationServiceException("Education with " + educationId + "doesn't exist!");
        }

        education.setName(educationDTO.getName());
        education.setDescription(educationDTO.getDescription());

        return EducationMapper.INSTANCE.educationToEducationDTO(educationRepository.save(education));
    }

    @Override
    public List<EducationDTO> getEducationsForResume(String resumeId) {

        List<Education> educations = educationRepository.getAllByResume_ResumeId(resumeId);

        return EducationMapper.INSTANCE.educationListToEducationDTOList(educations);
    }

    @Override
    public EducationDTO addEducation(EducationDTO educationDTO, String resumeId) {
        Resume resume = resumeRepository.findByResumeId(resumeId);

        if (resume == null) {
            throw new ResumeServiceException("Resume with " + resumeId + "doesn't exist!");
        }

        Education education = EducationMapper.INSTANCE.educationDTOToEducation(educationDTO);
        education.setEducationId(utils.generateEducationId(30));

        resume.addEducation(education);
        education.setResume(resume);

        return EducationMapper.INSTANCE.educationToEducationDTO(educationRepository.save(education));
    }

    @Override
    public List<EducationDTO> deleteEducationById(String resumeId, String educationId) {

        Education byEducationId = educationRepository.findByEducationId(educationId);

        if (byEducationId == null) {
            throw new EducationServiceException("Education with " + educationId + "doesn't exist!");
        }

        educationRepository.deleteById(byEducationId.getId());

        return EducationMapper.INSTANCE.educationListToEducationDTOList(resumeRepository.findByResumeId(resumeId).getEducations());
    }

}
