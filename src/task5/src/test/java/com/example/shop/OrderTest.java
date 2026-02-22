package com.example.shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderTest {

    @Test
    void initialStatus_isCreated() {
        Order order = new Order();

        assertEquals(OrderStatus.CREATED, order.getStatus());
    }

    @Test
    void addItem_whenCreated_succeeds() {
        Order order = new Order();

        order.addItem(new OrderItem("Notebook", 1, 10.0));

        assertEquals(1, order.getItems().size());
    }

    @Test
    void addItem_afterProcessed_throwsIllegalStateException() {
        Order order = new Order();
        order.setStatus(OrderStatus.PAID);

        assertThrows(IllegalStateException.class, () -> order.addItem(new OrderItem("Pen", 1, 1.0)));
    }
}
