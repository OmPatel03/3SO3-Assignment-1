package com.example.shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PaymentValidatorTest {

    private final PaymentValidator paymentValidator = new PaymentValidator();

    @Test
    void isPaymentMethodValid_null_returnsFalse() {
        assertFalse(paymentValidator.isPaymentMethodValid(null));
    }

    @Test
    void isPaymentMethodValid_card_returnsTrue() {
        assertTrue(paymentValidator.isPaymentMethodValid("card"));
    }

    @Test
    void isPaymentMethodValid_paypal_returnsTrue() {
        assertTrue(paymentValidator.isPaymentMethodValid("paypal"));
    }

    @Test
    void isPaymentMethodValid_crypto_returnsFalse() {
        assertFalse(paymentValidator.isPaymentMethodValid("crypto"));
    }

   @Test
   void isPaymentMethodValid_unknownMethod_throwsUnsupportedOperationException() {
       assertThrows(UnsupportedOperationException.class, () -> paymentValidator.isPaymentMethodValid("cash"));
   }

    @Test
    void isPaymentMethodValid_caseInsensitiveValidMethods_work() {
        assertTrue(paymentValidator.isPaymentMethodValid("CaRd"));
        assertTrue(paymentValidator.isPaymentMethodValid("PayPal"));
    }
}
