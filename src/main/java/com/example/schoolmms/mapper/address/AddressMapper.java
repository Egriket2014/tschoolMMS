package com.example.schoolmms.mapper.address;

import com.example.schoolmms.dto.address.AddressDto;
import com.example.schoolmms.entity.Address;
import com.example.schoolmms.mapper.IMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper extends IMapper<AddressDto, Address> {

    AddressMapper ADDRESS_MAPPER_INSTANCE = Mappers.getMapper(AddressMapper.class);
}
