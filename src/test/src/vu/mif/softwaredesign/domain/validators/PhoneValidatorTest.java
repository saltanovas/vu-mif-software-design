package vu.mif.softwaredesign.domain.validators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneValidatorTest {

    private PhoneValidator phoneValidator;

    @BeforeEach
    public void init() {
        String prefix = "LT";
        int length = 12;

        phoneValidator = new PhoneValidator(prefix, length);
    }

    @Test
    @ValueSource(strings = {"+370tyuioplk", "-3700000000"})
    public void hasNotOnlyNumbers(String phoneNumber) {
        assertFalse(phoneValidator.isValid(phoneNumber));
    }

    @Test
    @ValueSource(strings = {"800000000", "+3700000000"})
    public void hasOnlyNumber(String phoneNumber) {
        assertFalse(phoneValidator.isValid(phoneNumber));
    }

    @Test
    public void doesNotConvertsNumber8() {
        assertNotEquals("8", phoneValidator.convert("8"));
    }

    @Test
    public void doesNotConvertsNumber80() {
        assertEquals("80", phoneValidator.convert("80"));
    }

    @Test
    public void convertsValidNumber() {
        assertEquals("+37000000000", phoneValidator.convert("800000000"));
    }
}
