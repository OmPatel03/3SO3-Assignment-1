package com.example.shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PricingServiceTest {

    private static final double EPSILON = 1e-9;
    private final PricingService pricingService = new PricingService();

   @Test
   void calculateSubtotal_emptyOrder_returnsZero() {
       Order order = new Order();

       double subtotal = pricingService.calculateSubtotal(order);

       assertEquals(0.0, subtotal, EPSILON);
   }

   @Test
   void calculateSubtotal_multipleItems_returnsSum() {
       Order order = new Order();
       order.addItem(new OrderItem("Book", 2, 10.0));
       order.addItem(new OrderItem("Pen", 3, 5.0));

       double subtotal = pricingService.calculateSubtotal(order);

       assertEquals(35.0, subtotal, EPSILON);
   }

   @Test
   void calculateTax_negativeSubtotal_throwsIllegalArgumentException() {
       assertThrows(IllegalArgumentException.class, () -> pricingService.calculateTax(-1.0));
   }

   @Test
   void calculateTax_zeroSubtotal_returnsZero() {
       double tax = pricingService.calculateTax(0.0);

       assertEquals(0.0, tax, EPSILON);
   }

   @Test
   void calculateTax_positiveSubtotal_returnsTwentyPercent() {
       double tax = pricingService.calculateTax(50.0);

       assertEquals(10.0, tax, EPSILON);
   }
}
