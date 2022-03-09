package com.example.schoolmms.service.product;

import com.example.schoolmms.dto.product.BrandDto;
import com.example.schoolmms.entity.Brand;
import com.example.schoolmms.mapper.product.BrandMapper;
import com.example.schoolmms.repository.product.BrandRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepositoryImpl brandRepository;
    private final BrandMapper brandMapper;

    public long getNumberOfEntity() {
        return brandRepository.count();
    }

    public List<BrandDto> getAllBrand() {
        return brandMapper.toDtoList(brandRepository.findAll());
    }

    public BrandDto getBrandById(Long id) {
        return brandRepository.findById(id)
                .map(brandMapper::toDto)
                .orElse(null);
    }

    public BrandDto getBrandByName(String name) {
        return brandRepository.findByName(name)
                .map(brandMapper::toDto)
                .orElse(null);
    }

    public Brand getEntityByName(String name) {
        return brandRepository.findByName(name)
                .orElse(null);
    }

    @Transactional
    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }

    @Transactional
    public void addBrand(BrandDto brandDto) {
        Brand brand = new Brand();
        brand.setBrandName(brandDto.getBrandName());
        brandRepository.save(brand);
    }

    @Transactional
    public void updateBrand(Long id) {
        brandRepository.findById(id).ifPresent(brandRepository::update);
    }
}
