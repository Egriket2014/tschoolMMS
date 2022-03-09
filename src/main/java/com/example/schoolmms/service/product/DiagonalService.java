package com.example.schoolmms.service.product;

import com.example.schoolmms.dto.product.DiagonalDto;
import com.example.schoolmms.entity.Diagonal;
import com.example.schoolmms.mapper.product.DiagonalMapper;
import com.example.schoolmms.repository.product.DiagonalRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiagonalService {

    private final DiagonalRepositoryImpl diagonalRepository;
    private final DiagonalMapper diagonalMapper;

    public long getNumberOfEntity() {
        return diagonalRepository.count();
    }

    public List<DiagonalDto> getAllDiagonal() {
        return diagonalMapper.toDtoList(diagonalRepository.findAll());
    }

    public DiagonalDto getDiagonalById(Long id) {
        return diagonalRepository.findById(id)
                .map(diagonalMapper::toDto)
                .orElse(null);
    }

    public DiagonalDto getDiagonalByName(String name) {
        return diagonalRepository.findByName(name)
                .map(diagonalMapper::toDto)
                .orElse(null);
    }

    public Diagonal getEntityByName(String name) {
        return diagonalRepository.findByName(name)
                .orElse(null);
    }

    @Transactional
    public void deleteDiagonal(Long id) {
        diagonalRepository.deleteById(id);
    }

    @Transactional
    public void addDiagonal(DiagonalDto diagonalDto) {
        Diagonal diagonal = new Diagonal();
        diagonal.setDiagonal(diagonalDto.getDiagonal());
        diagonalRepository.save(diagonal);
    }

    @Transactional
    public void updateDiagonal(Long id) {
        diagonalRepository.findById(id).ifPresent(diagonalRepository::update);
    }
}
