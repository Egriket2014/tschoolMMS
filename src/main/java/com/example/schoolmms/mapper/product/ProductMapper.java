package com.example.schoolmms.mapper.product;

import com.example.schoolmms.dto.product.ProductDto;
import com.example.schoolmms.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        BrandMapper.class,
        CategoryMapper.class,
        DiagonalMapper.class,
        FrequencyMapper.class,
        MatrixMapper.class,
        ResolutionMapper.class
})
public interface ProductMapper {

    ProductMapper PRODUCT_MAPPER_INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto toDto(Product product);

    List<ProductDto> toDtoList(List<Product> productList);

    Product toEntity(ProductDto productDto);

    List<Product> toEntityList(List<ProductDto> productDtoList);
}
