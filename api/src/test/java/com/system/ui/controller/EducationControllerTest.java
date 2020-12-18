package com.system.ui.controller;

import com.system.service.impl.EducationServiceImpl;
import com.system.shared.dto.EducationDTO;
import com.system.ui.model.request.EducationDetailsRequestModel;
import com.system.ui.model.response.EducationRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class EducationControllerTest {

    @InjectMocks
    EducationController controller;
    @Mock
    EducationServiceImpl educationService;

    EducationDTO educationDTO1;
    EducationDTO educationDTO2;

    EducationDetailsRequestModel educationDetailsRequestModel;

    String userId = "ajshdjsadhjsad";
    String resumeId = "asdasgasda21";
    String educationId = "adhjsahdojsar3";


    List<EducationDTO> educationDTOList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        educationDTO1 = new EducationDTO();
        educationDTO1.setId(1L);
        educationDTO1.setName("education1");
        educationDTO1.setEducationId(educationId);

        educationDTO2 = new EducationDTO();
        educationDTO2.setId(2L);
        educationDTO2.setName("education2");
        educationDTO2.setEducationId(educationId + "2");

        educationDTOList.addAll(Arrays.asList(educationDTO1, educationDTO2));

        educationDetailsRequestModel = new EducationDetailsRequestModel();
        educationDetailsRequestModel.setName("education1");

    }

    @Test
    void getResumeEducations() {
        when(educationService.getEducationsForResume(anyString())).thenReturn(educationDTOList);

        List<EducationRest> resumeEducations = controller.getResumeEducations(resumeId);

        assertEquals(resumeEducations.size(), educationDTOList.size());
    }

    @Test
    void getAllEducationsForUserResumes() {
        when(educationService.getEducationsForAccountResumes(anyString())).thenReturn(educationDTOList);

        List<EducationRest> allEducationsForUserResumes = controller.getAllEducationsForAccountResumes((userId));

        assertEquals(allEducationsForUserResumes.size(), educationDTOList.size());
    }

    @Test
    void getEducationById() {
        when(educationService.getEducationById(anyString())).thenReturn(educationDTO1);

        EducationRest educationById = controller.getEducationById(educationId);

        assertNotNull(educationById);

        assertEquals(educationById.getEducationId(), educationDTO1.getEducationId());
        assertEquals(educationById.getName(), educationDTO1.getName());


    }

    @Test
    void updateEducation() {
        when(educationService.updateEducation(any(EducationDTO.class), anyString())).thenReturn(educationDTO1);

        EducationRest educationRest = controller.updateEducation(educationDetailsRequestModel, educationId);

        assertNotNull(educationRest);
        assertEquals(educationRest.getName(), educationDTO1.getName());
    }

    @Test
    void deleteEducation() {
        when(educationService.deleteEducationById(anyString(),anyString())).thenReturn(educationDTOList);

        List<EducationRest> educationRests = controller.deleteEducation(resumeId, educationId);

        assertEquals(educationRests.size(), educationDTOList.size());
    }

}