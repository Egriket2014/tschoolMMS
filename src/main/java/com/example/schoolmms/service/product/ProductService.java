package com.example.schoolmms.service.product;

import com.example.schoolmms.dto.product.ProductDto;
import com.example.schoolmms.dto.product.ProductParamDto;
import com.example.schoolmms.entity.Product;
import com.example.schoolmms.mapper.product.*;
import com.example.schoolmms.repository.product.ProductRepositoryImpl;
import com.example.schoolmms.repository.product.filter.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepositoryImpl productRepository;
    private final ProductMapper productMapper;

    private final BrandService brandService;
    private final CategoryService categoryService;
    private final DiagonalService diagonalService;
    private final FrequencyService frequencyService;
    private final MatrixService matrixService;
    private final ResolutionService resolutionService;

    private final BrandMapper brandMapper;
    private final CategoryMapper categoryMapper;
    private final DiagonalMapper diagonalMapper;
    private final FrequencyMapper frequencyMapper;
    private final MatrixMapper matrixMapper;
    private final ResolutionMapper resolutionMapper;

    public long getNumberOfEntity() {
        return productRepository.count();
    }

    public List<ProductDto> getAllProduct() {
        return productMapper.toDtoList(productRepository.findAll());
    }

    public ProductDto getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElse(null);
    }

    public List<ProductDto> getProductByParam(ProductParamDto params) {
        List<SearchCriteria> searchCriteriaList = new ArrayList<>();

        if (params.getCategory() != null)
            searchCriteriaList.add(new SearchCriteria("category", ":",
                    categoryService.getEntityByName(params.getCategory())));

        if (params.getBrand() != null)
            searchCriteriaList.add(new SearchCriteria("brand", ":",
                    brandService.getEntityByName(params.getBrand())));

        if (params.getResolution() != null)
            searchCriteriaList.add(new SearchCriteria("resolution", ":",
                    resolutionService.getEntityByName(params.getResolution())));

        if (params.getMatrix() != null)
            searchCriteriaList.add(new SearchCriteria("matrix", ":",
                    matrixService.getEntityByName(params.getMatrix())));

        if (params.getDiagonal() != null)
            searchCriteriaList.add(new SearchCriteria("diagonal", ":",
                    diagonalService.getEntityByName(params.getDiagonal())));

        if (params.getFrequency() != null)
            searchCriteriaList.add(new SearchCriteria("frequency", ":",
                    frequencyService.getEntityByName(params.getFrequency())));

        return productMapper.toDtoList(productRepository.findByParam(searchCriteriaList));
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void addProduct(ProductDto productDto) {
        Product product = Product.builder()
                .active(true)
                .productTitle(productDto.getProductTitle())
                .price(productDto.getPrice())
                .category(categoryMapper.toEntity(productDto.getCategory()))
                .brand(brandMapper.toEntity(productDto.getBrand()))
                .resolution(resolutionMapper.toEntity(productDto.getResolution()))
                .diagonal(diagonalMapper.toEntity(productDto.getDiagonal()))
                .matrix(matrixMapper.toEntity(productDto.getMatrix()))
                .frequency(frequencyMapper.toEntity(productDto.getFrequency()))
                .amount(productDto.getAmount())
                .build();

        productRepository.save(product);
    }

    @Transactional
    public void updateProduct(Long id) {
        productRepository.findById(id).ifPresent(productRepository::update);
    }
}
