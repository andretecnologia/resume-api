package com.system.ui.controller;

import com.system.service.ResumeService;
import com.system.shared.dto.ResumeDTO;
import com.system.shared.mapper.ResumeMapper;
import com.system.ui.model.request.ResumeDetailsRequestModel;
import com.system.ui.model.response.ResumeRest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/resumes")
public class ResumeController {

    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping(path = "/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ResumeRest> getaccountResumes(@PathVariable String accountId) {

        List<ResumeDTO> resumeDTOS = resumeService.getAccountResumes(accountId);

        return ResumeMapper.INSTANCE.resumeDTOListToResumeRestList(resumeDTOS);
    }


    @GetMapping(path = "/resume/{resumeId}")
    public ResumeRest getResumeById(@PathVariable String resumeId) {

        ResumeDTO resumeDTO = resumeService.getResumeById(resumeId);

        return ResumeMapper.INSTANCE.resumeDTOToResumeRest(resumeDTO);
    }



    @DeleteMapping(path = "/{accountId}/{resumeId}")
    public List<ResumeRest> deleteResume(@PathVariable String resumeId,
                                           @PathVariable String accountId) {

        List<ResumeDTO> resumeDTOS = resumeService.deleteResumeById(accountId, resumeId);

        return ResumeMapper.INSTANCE.resumeDTOListToResumeRestList(resumeDTOS);
    }



    @PostMapping(path = "/{accountId}")
    public ResumeRest createResume(@RequestBody ResumeDetailsRequestModel resumeDetails,
                                     @PathVariable String accountId) {

        ResumeDTO resumeDTO = ResumeMapper.INSTANCE.resumeDetailsToResumeDTO(resumeDetails);


        return ResumeMapper.INSTANCE.resumeDTOToResumeRest(resumeService.addResume(resumeDTO, accountId));
    }



    @PutMapping(path = "/{resumeId}")
    public ResumeRest updateResume(@RequestBody ResumeDetailsRequestModel resumeDetails,
                                     @PathVariable String resumeId) {

        ResumeDTO resumeDTO = ResumeMapper.INSTANCE.resumeDetailsToResumeDTO(resumeDetails);

        ResumeDTO returnValue = resumeService.updateResume(resumeDTO, resumeId);

        return ResumeMapper.INSTANCE.resumeDTOToResumeRest(returnValue);

    }
}
