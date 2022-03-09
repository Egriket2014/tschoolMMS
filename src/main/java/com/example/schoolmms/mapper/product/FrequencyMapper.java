package com.example.schoolmms.mapper.product;

import com.example.schoolmms.dto.product.FrequencyDto;
import com.example.schoolmms.entity.Frequency;
import com.example.schoolmms.mapper.IMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FrequencyMapper extends IMapper<FrequencyDto, Frequency> {

    FrequencyMapper FREQUENCY_MAPPER_INSTANCE = Mappers.getMapper(FrequencyMapper.class);
}
