package com.orderitem.controller;

import com.orderitem.entity.OrderItem;
import com.orderitem.exception.OrderItemNotFoundException;
import com.orderitem.service.OrderItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/orderitem")
public class OrderItemController {

    Logger logger = LoggerFactory.getLogger(OrderItemController.class);

    @Autowired
    OrderItemService orderItemService;

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody OrderItem orderItem) {
        logger.debug("Request object {} ", orderItem);
        OrderItem updatedItem = orderItemService.save(orderItem);
        logger.info("Order items added successfully.");
        return ResponseEntity.ok(updatedItem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable int id) throws OrderItemNotFoundException {
        return ResponseEntity.ok(orderItemService.get(id));
    }
}
