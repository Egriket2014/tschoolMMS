package com.example.schoolmms.mapper.product;

import com.example.schoolmms.dto.product.CategoryDto;
import com.example.schoolmms.entity.Category;
import com.example.schoolmms.mapper.IMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends IMapper<CategoryDto, Category> {

    CategoryMapper CATEGORY_MAPPER_INSTANCE = Mappers.getMapper(CategoryMapper.class);
}
