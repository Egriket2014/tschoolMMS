package com.example.schoolmms.mapper.address;

import com.example.schoolmms.dto.address.AddressDto;
import com.example.schoolmms.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressMapper ADDRESS_MAPPER_INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressDto toDto(Address address);

    List<AddressDto> toDtoList(List<Address> addressList);

    Address toEntity(AddressDto addressDto);
}
