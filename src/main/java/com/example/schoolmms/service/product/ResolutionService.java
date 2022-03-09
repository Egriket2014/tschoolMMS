package com.example.schoolmms.service.product;

import com.example.schoolmms.dto.product.ResolutionDto;
import com.example.schoolmms.entity.Resolution;
import com.example.schoolmms.mapper.product.ResolutionMapper;
import com.example.schoolmms.repository.product.ResolutionRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResolutionService {

    private final ResolutionRepositoryImpl resolutionRepository;
    private final ResolutionMapper resolutionMapper;

    public long getNumberOfEntity() {
        return resolutionRepository.count();
    }

    public List<ResolutionDto> getAllResolution() {
        return resolutionMapper.toDtoList(resolutionRepository.findAll());
    }

    public ResolutionDto getResolutionById(Long id) {
        return resolutionRepository.findById(id)
                .map(resolutionMapper::toDto)
                .orElse(null);
    }

    public ResolutionDto getResolutionByName(String name) {
        return resolutionRepository.findByName(name)
                .map(resolutionMapper::toDto)
                .orElse(null);
    }

    public Resolution getEntityByName(String name) {
        return resolutionRepository.findByName(name)
                .orElse(null);
    }

    @Transactional
    public void deleteResolution(Long id) {
        resolutionRepository.deleteById(id);
    }

    @Transactional
    public void addResolution(ResolutionDto resolutionDto) {
        Resolution resolution = new Resolution();
        resolution.setResolution(resolutionDto.getResolution());
        resolutionRepository.save(resolution);
    }

    @Transactional
    public void updateResolution(Long id) {
        resolutionRepository.findById(id).ifPresent(resolutionRepository::update);
    }
}
