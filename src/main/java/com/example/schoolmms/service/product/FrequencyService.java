package com.example.schoolmms.service.product;

import com.example.schoolmms.dto.product.FrequencyDto;
import com.example.schoolmms.entity.Frequency;
import com.example.schoolmms.mapper.product.FrequencyMapper;
import com.example.schoolmms.repository.product.FrequencyRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FrequencyService {

    private final FrequencyRepositoryImpl frequencyRepository;
    private final FrequencyMapper frequencyMapper;

    public long getNumberOfEntity() {
        return frequencyRepository.count();
    }

    public List<FrequencyDto> getAllFrequency() {
        return frequencyMapper.toDtoList(frequencyRepository.findAll());
    }

    public FrequencyDto getFrequencyById(Long id) {
        return frequencyRepository.findById(id)
                .map(frequencyMapper::toDto)
                .orElse(null);
    }

    public FrequencyDto getFrequencyByName(String name) {
        return frequencyRepository.findByName(name)
                .map(frequencyMapper::toDto)
                .orElse(null);
    }

    public Frequency getEntityByName(String name) {
        return frequencyRepository.findByName(name)
                .orElse(null);
    }

    @Transactional
    public void deleteFrequency(Long id) {
        frequencyRepository.deleteById(id);
    }

    @Transactional
    public void addFrequency(FrequencyDto frequencyDto) {
        Frequency frequency = new Frequency();
        frequency.setFrequency(frequencyDto.getFrequency());
        frequencyRepository.save(frequency);
    }

    @Transactional
    public void updateFrequency(Long id) {
        frequencyRepository.findById(id).ifPresent(frequencyRepository::update);
    }
}
