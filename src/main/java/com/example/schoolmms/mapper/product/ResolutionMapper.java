package com.example.schoolmms.mapper.product;

import com.example.schoolmms.dto.product.ResolutionDto;
import com.example.schoolmms.entity.Resolution;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResolutionMapper {

    ResolutionMapper RESOLUTION_MAPPER_INSTANCE = Mappers.getMapper(ResolutionMapper.class);

    ResolutionDto toDto(Resolution resolution);

    List<ResolutionDto> toDtoList(List<Resolution> resolutionList);

    Resolution toEntity(ResolutionDto resolutionDto);
}
