package com.example.shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DiscountServiceTest {

    private static final double EPSILON = 1e-9;
    private final DiscountService discountService = new DiscountService();

    @Test
    void applyDiscount_nullCode_returnsUnchangedSubtotal() {
        double result = discountService.applyDiscount(100.0, null);

        assertEquals(100.0, result, EPSILON);
    }

    @Test
    void applyDiscount_blankCode_returnsUnchangedSubtotal() {
        double result = discountService.applyDiscount(100.0, "   ");

        assertEquals(100.0, result, EPSILON);
    }

    @Test
    void applyDiscount_student10_returnsTenPercentOff() {
        double result = discountService.applyDiscount(100.0, "STUDENT10");

        assertEquals(90.0, result, EPSILON);
    }

    @Test
    void applyDiscount_blackFriday_returnsThirtyPercentOff() {
        double result = discountService.applyDiscount(100.0, "BLACKFRIDAY");

        assertEquals(70.0, result, EPSILON);
    }

    @Test
    void applyDiscount_caseInsensitiveCodeHandling_works() {
        double student = discountService.applyDiscount(100.0, "student10");
        double blackFriday = discountService.applyDiscount(100.0, "blackfriday");

        assertEquals(90.0, student, EPSILON);
        assertEquals(70.0, blackFriday, EPSILON);
    }

    @Test
    void applyDiscount_invalidCode_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> discountService.applyDiscount(100.0, "INVALID"));
    }

    @Test
    void applyDiscount_unknownCode_returnsUnchangedSubtotal() {
        double result = discountService.applyDiscount(100.0, "SUMMERSALE");

        assertEquals(100.0, result, EPSILON);
    }
}
