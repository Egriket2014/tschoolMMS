package com.example.schoolmms.mapper.product;

import com.example.schoolmms.dto.product.BrandDto;
import com.example.schoolmms.entity.Brand;
import com.example.schoolmms.mapper.IMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BrandMapper extends IMapper<BrandDto, Brand> {

    BrandMapper BRAND_MAPPER_INSTANCE = Mappers.getMapper(BrandMapper.class);
}
