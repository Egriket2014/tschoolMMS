package com.example.schoolmms.service.order;

import com.example.schoolmms.dto.order.OrderDto;
import com.example.schoolmms.dto.product.ProductDto;
import com.example.schoolmms.entity.Order;
import com.example.schoolmms.mapper.address.AddressMapper;
import com.example.schoolmms.mapper.order.OrderMapper;
import com.example.schoolmms.mapper.product.ProductMapper;
import com.example.schoolmms.mapper.user.UserMapper;
import com.example.schoolmms.repository.order.OrderRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepositoryImpl orderRepository;
    private final OrderMapper orderMapper;

    private final UserMapper userMapper;
    private final AddressMapper addressMapper;
    private final ProductMapper productMapper;

    public long getNumberOfEntity() {
        return orderRepository.count();
    }

    public List<OrderDto> getAllOrder() {
        return orderMapper.toDtoList(orderRepository.findAll());
    }

    public OrderDto getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::toDto)
                .orElse(null);
    }

    @Transactional
    public void addOrder(OrderDto orderDto) {
        Order order = Order.builder()
                .user(userMapper.toEntity(orderDto.getUser()))
                .address(addressMapper.toEntity(orderDto.getAddress()))
                .paymentMethod(orderDto.getPaymentMethod())
                .deliveryMethod(orderDto.getDeliveryMethod())
                .products(productMapper.toEntityList(orderDto.getProducts()))
                .paymentStatus(orderDto.getPaymentStatus())
                .orderStatus(orderDto.getOrderStatus())
                .build();

        orderRepository.save(order);
    }

    @Transactional
    public void addAllOrder(List<OrderDto> orderDtoList, Long userId) {
        orderDtoList.forEach(orderDto -> addOrder(orderDto));
    }

    @Transactional
    public void addProductInOrder(ProductDto productDto, long orderId) {

    }

    @Transactional
    public void removeProductInOrder(ProductDto productDto, long orderId) {

    }

    public List<OrderDto> getOrdersByUserId(Long userId) {
        return orderMapper.toDtoList(orderRepository.findByUserId(userId));
    }

    @Transactional
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Transactional
    public void updateOrder(Long id) {
        orderRepository.findById(id).ifPresent(orderRepository::update);
    }

}
