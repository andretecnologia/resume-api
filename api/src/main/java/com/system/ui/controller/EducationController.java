package com.system.ui.controller;

import com.system.service.EducationService;
import com.system.shared.dto.EducationDTO;
import com.system.shared.mapper.EducationMapper;
import com.system.ui.model.request.EducationDetailsRequestModel;
import com.system.ui.model.response.EducationRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/educations")
public class EducationController {

    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping(path = "/{resumeId}")
    public List<EducationRest> getResumeEducations(@PathVariable String resumeId) {

        List<EducationDTO> educationDTOList = educationService.getEducationsForResume(resumeId);

        return EducationMapper.INSTANCE.educationDTOListToEducationRestList(educationDTOList);
    }

    @GetMapping(path = "/account/{accountId}")
    public List<EducationRest> getAllEducationsForAccountResumes(@PathVariable String accountId) {

        List<EducationDTO> educationDTOList = educationService.getEducationsForAccountResumes(accountId);

        return EducationMapper.INSTANCE.educationDTOListToEducationRestList(educationDTOList);
    }

    @GetMapping(path = "/education/{educationId}")
    public EducationRest getEducationById(@PathVariable String educationId) {

        EducationDTO educationDTO = educationService.getEducationById(educationId);

        return EducationMapper.INSTANCE.educationDTOToEducationRest(educationDTO);
    }

    @PutMapping(path = "/{educationId}")
    public EducationRest updateEducation(@RequestBody EducationDetailsRequestModel educationDetailsRequestModel,
                               @PathVariable String educationId) {

        EducationDTO educationDTO = EducationMapper.INSTANCE.educationDetailRequestToEducationDTO(educationDetailsRequestModel);

        EducationDTO updateEducation = educationService.updateEducation(educationDTO, educationId);

        return EducationMapper.INSTANCE.educationDTOToEducationRest(updateEducation);

    }


    @DeleteMapping(path = "/{resumeId}/{educationId}")
    public List<EducationRest> deleteEducation(@PathVariable String resumeId,
                                     @PathVariable String educationId) {

        List<EducationDTO> educationDTOList = educationService.deleteEducationById(resumeId,educationId);

        return EducationMapper.INSTANCE.educationDTOListToEducationRestList(educationDTOList);

    }


    @PostMapping(path = "/{resumeId}")
    public ResponseEntity<?> addEducation(@RequestBody EducationDetailsRequestModel educationDetailsRequestModel,
                                     @PathVariable String resumeId) {

        EducationDTO educationDTO = EducationMapper.INSTANCE.educationDetailRequestToEducationDTO(educationDetailsRequestModel);
        educationService.addEducation(educationDTO, resumeId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
