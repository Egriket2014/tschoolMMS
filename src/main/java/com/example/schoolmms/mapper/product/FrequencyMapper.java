package com.example.schoolmms.mapper.product;

import com.example.schoolmms.dto.product.FrequencyDto;
import com.example.schoolmms.entity.Frequency;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FrequencyMapper {

    FrequencyMapper FREQUENCY_MAPPER_INSTANCE = Mappers.getMapper(FrequencyMapper.class);

    FrequencyDto toDto(Frequency frequency);

    List<FrequencyDto> toDtoList(List<Frequency> frequencyList);

    Frequency toEntity(FrequencyDto frequencyDto);
}
