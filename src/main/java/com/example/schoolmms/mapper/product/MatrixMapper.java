package com.example.schoolmms.mapper.product;

import com.example.schoolmms.dto.product.MatrixDto;
import com.example.schoolmms.entity.Matrix;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MatrixMapper {

    MatrixMapper MATRIX_MAPPER_INSTANCE = Mappers.getMapper(MatrixMapper.class);

    MatrixDto toDto(Matrix matrix);

    List<MatrixDto> toDtoList(List<Matrix> matrixList);

    Matrix toEntity(MatrixDto matrixDto);
}
