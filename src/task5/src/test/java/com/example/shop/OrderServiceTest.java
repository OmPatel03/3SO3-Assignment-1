package com.example.shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderServiceTest {

    private static final double EPSILON = 1e-9;
    private final OrderService orderService = new OrderService();

   @Test
   void processOrder_cryptoPayment_returnsZeroAndCancelsOrder() {
       Order order = createSampleOrder();

       double total = orderService.processOrder(order, null, "crypto");

       assertEquals(0.0, total, EPSILON);
       assertEquals(OrderStatus.CANCELLED, order.getStatus());
   }
//
   @Test
   void processOrder_nullPayment_returnsZeroAndCancelsOrder() {
       Order order = createSampleOrder();

       double total = orderService.processOrder(order, null, null);

       assertEquals(0.0, total, EPSILON);
       assertEquals(OrderStatus.CANCELLED, order.getStatus());
   }

   @Test
   void processOrder_unknownPayment_throwsAndOrderRemainsCreated() {
       Order order = createSampleOrder();

       assertThrows(UnsupportedOperationException.class, () -> orderService.processOrder(order, null, "cash"));
       assertEquals(OrderStatus.CREATED, order.getStatus());
   }

   @Test
   void processOrder_validNoDiscount_returnsSubtotalPlusTaxAndMarksPaid() {
       Order order = createSampleOrder();

       double total = orderService.processOrder(order, null, "card");

       assertEquals(120.0, total, EPSILON);
       assertEquals(OrderStatus.PAID, order.getStatus());
   }
//
   @Test
   void processOrder_studentDiscount_returnsDiscountedTotalPlusTaxAndMarksPaid() {
       Order order = createSampleOrder();

       double total = orderService.processOrder(order, "STUDENT10", "card");

       assertEquals(108.0, total, EPSILON);
       assertEquals(OrderStatus.PAID, order.getStatus());
   }

   @Test
   void processOrder_blackFridayDiscount_returnsDiscountedTotalPlusTaxAndMarksPaid() {
       Order order = createSampleOrder();

       double total = orderService.processOrder(order, "BLACKFRIDAY", "paypal");

       assertEquals(84.0, total, EPSILON);
       assertEquals(OrderStatus.PAID, order.getStatus());
   }

   @Test
   void processOrder_invalidDiscount_throwsAndOrderRemainsCreated() {
       Order order = createSampleOrder();

       assertThrows(IllegalArgumentException.class, () -> orderService.processOrder(order, "INVALID", "card"));
       assertEquals(OrderStatus.CREATED, order.getStatus());
   }

   @Test
   void processOrder_emptyOrderWithValidPayment_returnsZeroAndMarksPaid() {
       Order order = new Order();

       double total = orderService.processOrder(order, null, "card");

       assertEquals(0.0, total, EPSILON);
       assertEquals(OrderStatus.PAID, order.getStatus());
   }

    private static Order createSampleOrder() {
        Order order = new Order();
        order.addItem(new OrderItem("Item-A", 2, 30.0));
        order.addItem(new OrderItem("Item-B", 1, 40.0));
        return order;
    }
}
