package com.example.schoolmms.mapper.product;

import com.example.schoolmms.dto.product.BrandDto;
import com.example.schoolmms.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    BrandMapper BRAND_MAPPER_INSTANCE = Mappers.getMapper(BrandMapper.class);

    BrandDto toDto(Brand brand);

    List<BrandDto> toDtoList(List<Brand> brandList);

    Brand toEntity(BrandDto brandDto);
}
