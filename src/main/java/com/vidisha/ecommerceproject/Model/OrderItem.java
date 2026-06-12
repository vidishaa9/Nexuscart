//package com.vidisha.ecommerceproject.Model;
//
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.math.BigDecimal;
//
//@Data
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//public class OrderItem {
//    @Id
//    private int id;
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;
//    private BigDecimal totalPrice;
//    @ManyToOne(fetch= FetchType.LAZY)
//    private Order order;
//}
