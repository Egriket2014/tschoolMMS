package com.example.schoolmms.service.product;

import com.example.schoolmms.dto.product.MatrixDto;
import com.example.schoolmms.entity.Matrix;
import com.example.schoolmms.mapper.product.MatrixMapper;
import com.example.schoolmms.repository.product.MatrixRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatrixService {

    private final MatrixRepositoryImpl matrixRepository;
    private final MatrixMapper matrixMapper;

    public long getNumberOfEntity() {
        return matrixRepository.count();
    }

    public List<MatrixDto> getAllMatrix() {
        return matrixMapper.toDtoList(matrixRepository.findAll());
    }

    public MatrixDto getMatrixById(Long id) {
        return matrixRepository.findById(id)
                .map(matrixMapper::toDto)
                .orElse(null);
    }

    public MatrixDto getMatrixByName(String name) {
        return matrixRepository.findByName(name)
                .map(matrixMapper::toDto)
                .orElse(null);
    }

    public Matrix getEntityByName(String name) {
        return matrixRepository.findByName(name)
                .orElse(null);
    }

    @Transactional
    public void deleteMatrix(Long id) {
        matrixRepository.deleteById(id);
    }

    @Transactional
    public void addMatrix(MatrixDto matrixDto) {
        Matrix matrix = new Matrix();
        matrix.setMatrix(matrixDto.getMatrix());
        matrixRepository.save(matrix);
    }

    @Transactional
    public void updateMatrix(Long id) {
        matrixRepository.findById(id).ifPresent(matrixRepository::update);
    }
}
