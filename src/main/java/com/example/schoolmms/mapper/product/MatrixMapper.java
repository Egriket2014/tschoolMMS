package com.example.schoolmms.mapper.product;

import com.example.schoolmms.dto.product.MatrixDto;
import com.example.schoolmms.entity.Matrix;
import com.example.schoolmms.mapper.IMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MatrixMapper extends IMapper<MatrixDto, Matrix> {

    MatrixMapper MATRIX_MAPPER_INSTANCE = Mappers.getMapper(MatrixMapper.class);
}
