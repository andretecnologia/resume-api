package com.system.service.impl;

import com.system.exceptions.ResumeServiceException;
import com.system.io.entity.Address;
import com.system.io.entity.Resume;
import com.system.io.entity.Account;
import com.system.io.repository.ResumeRepository;
import com.system.io.repository.AccountRepository;
import com.system.service.ResumeService;
import com.system.shared.Utils;
import com.system.shared.dto.AccountDTO;
import com.system.shared.dto.ResumeDTO;
import com.system.shared.mapper.ResumeMapper;
import com.system.shared.mapper.AccountMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResumeServiceImpl implements ResumeService {

    private final AccountRepository accountRepository;
    private final ResumeRepository resumeRepository;
    private final Utils utils;

    public ResumeServiceImpl(AccountRepository accountRepository, ResumeRepository resumeRepository, Utils utils) {
        this.accountRepository = accountRepository;
        this.resumeRepository = resumeRepository;
        this.utils = utils;
    }

    @Override
    public ResumeDTO addResume(ResumeDTO resumeDTO, String accountId) {
        Account account = accountRepository.findByAccountId(accountId);

        if (account == null) {
            throw new UsernameNotFoundException(accountId);
        }

        Address address = new Address();
        address.setName(resumeDTO.getAddress().getName());
        address.setNumber(resumeDTO.getAddress().getName());
        address.setAddressId(utils.generateAddressId(30));

        Resume resume = ResumeMapper.INSTANCE.resumeDTOToResume(resumeDTO);
        resume.setAddress(address);
        address.setResume(resume);
        resume.setResumeId(utils.generateResumeId(30));

        account.getResumes().add(resume);

        resume.setAccount(account);

        return ResumeMapper.INSTANCE.acountToResumeDTO(resumeRepository.save(resume));
    }

    @Override
    public ResumeDTO updateResume(ResumeDTO resumeDTO, String resumeId) {
        Resume resume = resumeRepository.findByResumeId(resumeId);

        if (resume == null) {
            throw new ResumeServiceException("Resume doesn't exist!");
        }

        resume.setName(resumeDTO.getName());
        resume.setType(resumeDTO.getType());
        resume.getAddress().setName(resumeDTO.getAddress().getName());
        resume.getAddress().setNumber(resumeDTO.getAddress().getNumber());

        return ResumeMapper.INSTANCE.acountToResumeDTO(resumeRepository.save(resume));
    }

    @Transactional
    @Override
    public List<ResumeDTO> deleteResumeById(String accountId, String resumeId) {

        if (resumeRepository.findByResumeId(resumeId) == null) {
            throw new ResumeServiceException("Resume with " + resumeId + "doesn't exist!");
        }

        resumeRepository.deleteResumeByResumeId(resumeId);

        return getAccountResumes(accountId);

    }

    @Override
    public ResumeDTO getResumeById(String resumeId) {

        if(resumeRepository.findByResumeId(resumeId) == null) {
            throw new ResumeServiceException("Resume with " + resumeId + "doesn't exist!");
        }

        Resume resume = resumeRepository.findByResumeId(resumeId);

        return ResumeMapper.INSTANCE.acountToResumeDTO(resume);
    }

    @Override
    public List<ResumeDTO> getAccountResumes(String id) {

        Account foundAccount = accountRepository.findByAccountId(id);

        if (foundAccount == null) {
            throw new ResumeServiceException("User with id: " + id + "doesn't exist!");
        }

        AccountDTO accountDTO = AccountMapper.INSTANCE.accountToAccountDto(foundAccount);

        return accountDTO.getResumes();
    }


}
