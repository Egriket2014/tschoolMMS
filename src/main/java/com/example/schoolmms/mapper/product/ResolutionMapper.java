package com.example.schoolmms.mapper.product;

import com.example.schoolmms.dto.product.ResolutionDto;
import com.example.schoolmms.entity.Resolution;
import com.example.schoolmms.mapper.IMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ResolutionMapper extends IMapper<ResolutionDto, Resolution> {

    ResolutionMapper RESOLUTION_MAPPER_INSTANCE = Mappers.getMapper(ResolutionMapper.class);
}
