package com.system.shared.mapper;

import com.system.io.entity.Resume;
import com.system.shared.dto.ResumeDTO;
import com.system.ui.model.request.ResumeDetailsRequestModel;
import com.system.ui.model.response.ResumeRest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel="spring")
public interface ResumeMapper {



    ResumeMapper INSTANCE = Mappers.getMapper( ResumeMapper.class );


    ResumeRest resumeDTOToResumeRest(ResumeDTO resumeDTO);

    Resume resumeDTOToResume(ResumeDTO resumeDTO);
    ResumeDTO acountToResumeDTO(Resume resume);

    ResumeDTO resumeDetailsToResumeDTO(ResumeDetailsRequestModel resumeDetailsRequestModel);

    List<ResumeRest> resumeDTOListToResumeRestList(List<ResumeDTO> resumeDTOS);

}
