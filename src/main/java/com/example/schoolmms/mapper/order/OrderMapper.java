package com.example.schoolmms.mapper.order;

import com.example.schoolmms.dto.order.OrderDto;
import com.example.schoolmms.entity.Order;
import com.example.schoolmms.mapper.IMapper;
import com.example.schoolmms.mapper.address.AddressMapper;
import com.example.schoolmms.mapper.product.ProductMapper;
import com.example.schoolmms.mapper.user.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {
        UserMapper.class,
        AddressMapper.class,
        ProductMapper.class
})
public interface OrderMapper extends IMapper<OrderDto, Order> {

    OrderMapper ORDER_MAPPER_INSTANCE = Mappers.getMapper(OrderMapper.class);
}
