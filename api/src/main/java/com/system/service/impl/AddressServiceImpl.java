package com.system.service.impl;


import com.system.exceptions.ResumeServiceException;
import com.system.io.entity.Resume;
import com.system.io.repository.ResumeRepository;
import com.system.service.AddressService;
import com.system.shared.dto.AddressDTO;
import com.system.shared.mapper.AddressMapper;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final ResumeRepository resumeRepository;

    public AddressServiceImpl(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    @Override
    public AddressDTO getAddress(String resumeId) {

        Resume resume = resumeRepository.findByResumeId(resumeId);

        if (resume ==null) {
            throw new ResumeServiceException("Resume doesnt exist!");
        }

        return AddressMapper.INSTANCE.addressToAddressDTO(resume.getAddress());
    }
}
