package com.example.schoolmms.mapper.product;

import com.example.schoolmms.dto.product.CategoryDto;
import com.example.schoolmms.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper CATEGORY_MAPPER_INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto toDto(Category category);

    List<CategoryDto> toDtoList(List<Category> categoryList);

    Category toEntity(CategoryDto categoryDto);
}
