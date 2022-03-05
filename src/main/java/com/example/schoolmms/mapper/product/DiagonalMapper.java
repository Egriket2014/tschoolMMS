package com.example.schoolmms.mapper.product;

import com.example.schoolmms.dto.product.DiagonalDto;
import com.example.schoolmms.entity.Diagonal;
import com.example.schoolmms.mapper.IMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DiagonalMapper extends IMapper<DiagonalDto, Diagonal> {

    DiagonalMapper DIAGONAL_MAPPER_INSTANCE = Mappers.getMapper(DiagonalMapper.class);
}
