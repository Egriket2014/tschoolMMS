package com.example.schoolmms.service.product;

import com.example.schoolmms.dto.product.CategoryDto;
import com.example.schoolmms.entity.Category;
import com.example.schoolmms.mapper.product.CategoryMapper;
import com.example.schoolmms.repository.product.CategoryRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepositoryImpl categoryRepository;
    private final CategoryMapper categoryMapper;

    public long getNumberOfEntity() {
        return categoryRepository.count();
    }

    public List<CategoryDto> getAllCategory() {
        return categoryMapper.toDtoList(categoryRepository.findAll());
    }

    public CategoryDto getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDto)
                .orElse(null);
    }

    public CategoryDto getCategoryByName(String name) {
        return categoryRepository.findByName(name)
                .map(categoryMapper::toDto)
                .orElse(null);
    }

    public Category getEntityByName(String name) {
        return categoryRepository.findByName(name)
                .orElse(null);
    }

    @Transactional
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Transactional
    public void addCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setCategoryName(categoryDto.getCategoryName());
        categoryRepository.save(category);
    }

    @Transactional
    public void updateCategory(Long id) {
        categoryRepository.findById(id).ifPresent(categoryRepository::update);
    }
}
