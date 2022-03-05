package com.example.schoolmms.dto.order;

import com.example.schoolmms.dto.address.AddressDto;
import com.example.schoolmms.dto.product.ProductDto;
import com.example.schoolmms.dto.user.UserDto;
import com.example.schoolmms.entity.enums.DeliveryMethod;
import com.example.schoolmms.entity.enums.OrderStatus;
import com.example.schoolmms.entity.enums.PaymentMethod;
import com.example.schoolmms.entity.enums.PaymentStatus;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {

    private Long id;
    private UserDto user;
    private AddressDto address;
    private PaymentMethod paymentMethod;
    private DeliveryMethod deliveryMethod;
    private List<ProductDto> products;
    private PaymentStatus paymentStatus;
    private OrderStatus orderStatus;
}
