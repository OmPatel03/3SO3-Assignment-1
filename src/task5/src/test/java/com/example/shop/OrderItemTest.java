package com.example.shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderItemTest {

    private static final double EPSILON = 1e-9;

    @Test
    void constructor_rejectsQuantityZero() {
        assertThrows(IllegalArgumentException.class, () -> new OrderItem("Item", 0, 10.0));
    }

    @Test
    void constructor_rejectsNegativeQuantity() {
        assertThrows(IllegalArgumentException.class, () -> new OrderItem("Item", -1, 10.0));
    }

    @Test
    void constructor_rejectsNegativeUnitPrice() {
        assertThrows(IllegalArgumentException.class, () -> new OrderItem("Item", 1, -0.01));
    }

//    @Test
//    void constructor_allowsZeroUnitPrice() {
//        OrderItem item = new OrderItem("Freebie", 2, 0.0);
//
//        assertEquals(0.0, item.getTotalPrice(), EPSILON);
//    }

    @Test
    void getTotalPrice_returnsQuantityTimesUnitPrice() {
        OrderItem item = new OrderItem("Item", 3, 19.99);

        assertEquals(59.97, item.getTotalPrice(), EPSILON);
    }

    @Test
    void getQuantity_returnsConstructorValue() {
        OrderItem item = new OrderItem("Item", 4, 2.0);

        assertEquals(4, item.getQuantity());
    }
}
