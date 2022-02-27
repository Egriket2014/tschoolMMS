package com.example.schoolmms.mapper.product;

import com.example.schoolmms.dto.product.DiagonalDto;
import com.example.schoolmms.entity.Diagonal;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DiagonalMapper {

    DiagonalMapper DIAGONAL_MAPPER_INSTANCE = Mappers.getMapper(DiagonalMapper.class);

    DiagonalDto toDto(Diagonal diagonal);

    List<DiagonalDto> toDtoList(List<Diagonal> diagonalList);

    Diagonal toEntity(DiagonalDto diagonalDto);
}
