package com.system.shared.mapper;

import com.system.io.entity.Education;
import com.system.shared.dto.EducationDTO;
import com.system.ui.model.request.EducationDetailsRequestModel;
import com.system.ui.model.response.EducationRest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel="spring")
public interface EducationMapper {


    EducationMapper INSTANCE = Mappers.getMapper( EducationMapper.class );

    EducationRest educationDTOToEducationRest(EducationDTO educationDTO);
    EducationDTO educationDetailRequestToEducationDTO(EducationDetailsRequestModel educationDetailsRequestModel);
    List<EducationRest> educationDTOListToEducationRestList(List<EducationDTO> educationDTOList);
    List<EducationDTO> educationListToEducationDTOList(List<Education> list);
    Education educationDTOToEducation(EducationDTO educationDTO);
    EducationDTO educationToEducationDTO(Education education);
}
