package com.orderitem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ORDER_ITEM")
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class OrderItem {

    @Id
    @GeneratedValue
    private Integer productCode;
    @NotNull
    @Min(value = 1, message = "Quantity should be greater than 1")
    private Integer quantity;
    @NotBlank(message = "Product name should not be blank")
    private String productName;
    @NotNull(message = "price should not be empty")
    private Double price;

}
