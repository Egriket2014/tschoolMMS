package com.example.schoolmms.mapper.product;

import com.example.schoolmms.dto.product.ProductDto;
import com.example.schoolmms.entity.Product;
import com.example.schoolmms.mapper.IMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {
        BrandMapper.class,
        CategoryMapper.class,
        DiagonalMapper.class,
        FrequencyMapper.class,
        MatrixMapper.class,
        ResolutionMapper.class
})
public interface ProductMapper extends IMapper<ProductDto, Product> {

    ProductMapper PRODUCT_MAPPER_INSTANCE = Mappers.getMapper(ProductMapper.class);
}
