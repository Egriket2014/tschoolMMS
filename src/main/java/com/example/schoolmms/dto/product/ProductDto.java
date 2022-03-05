package com.example.schoolmms.dto.product;

import lombok.Data;

@Data
public class ProductDto {

    private Long id;
    private String productTitle;
    private Double price;
    private CategoryDto category;
    private BrandDto brand;
    private ResolutionDto resolution;
    private DiagonalDto diagonal;
    private MatrixDto matrix;
    private FrequencyDto frequency;
    private Integer amount;
}
