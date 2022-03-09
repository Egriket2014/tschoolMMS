package com.example.schoolmms.service.address;

import com.example.schoolmms.dto.address.AddressDto;
import com.example.schoolmms.entity.Address;
import com.example.schoolmms.mapper.address.AddressMapper;
import com.example.schoolmms.mapper.user.UserMapper;
import com.example.schoolmms.repository.address.AddressRepositoryImpl;
import com.example.schoolmms.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepositoryImpl addressRepository;
    private final AddressMapper addressMapper;

    private final UserService userService;
    private final UserMapper userMapper;

    public long getNumberOfEntity() {
        return addressRepository.count();
    }

    public List<AddressDto> getAllAddress() {
        return addressMapper.toDtoList(addressRepository.findAll());
    }

    public AddressDto getAddressById(Long id) {
        return addressRepository.findById(id)
                .map(addressMapper::toDto)
                .orElse(null);
    }

    @Transactional
    public void addAddress(AddressDto addressDto, Long userId) {
        Address address = Address.builder()
                .countryName(addressDto.getCountryName())
                .cityName(addressDto.getCityName())
                .code(addressDto.getCode())
                .streetName(addressDto.getStreetName())
                .homeNumber(addressDto.getHomeNumber())
                .apartmentNumber(addressDto.getApartmentNumber())
                .user(userMapper.toEntity(userService.getUserById(userId)))
                .build();

        addressRepository.save(address);
    }

    @Transactional
    public void addAllAddress(List<AddressDto> addressDtoList, Long userId) {
        addressDtoList.forEach(addressDto -> addAddress(addressDto, userId));
    }


    public List<AddressDto> getByUserId(Long userId) {
        return addressMapper.toDtoList(addressRepository.findByUserId(userId));
    }

    @Transactional
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    @Transactional
    public void updateAddress(Long id) {
        addressRepository.findById(id).ifPresent(addressRepository::update);
    }
}
