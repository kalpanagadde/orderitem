package com.orderitem.service;

import com.orderitem.entity.OrderItem;
import com.orderitem.exception.OrderItemNotFoundException;
import com.orderitem.repository.OrderItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class OrderItemService {

    private final Logger logger = LoggerFactory.getLogger(OrderItemService.class);

    @Autowired
    OrderItemRepository orderItemRepository;

    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public OrderItem get(int id) {

        Optional<OrderItem> orderItem = orderItemRepository.findById(id);

        if (orderItem.isPresent())
            return orderItem.get();
        else {
            logger.error("Product not found with id {}", id);
            throw new OrderItemNotFoundException("Product not found with id : " + id);
        }
    }
}
