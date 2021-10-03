package vu.mif.lazdauskas.matas.softwaredesign.domain.validators;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneValidatorTest {

    PhoneValidator phoneValidator;
    @BeforeEach
    void setUp() {
        phoneValidator = new PhoneValidator();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void phoneValid_LT_ShouldBeTrue() {
        assertTrue(phoneValidator.validate("861765277", "+370", 12));
    }

    @Test
    void phoneValid_UK_ShouldBeTrue() {
        assertTrue(phoneValidator.validate("+447911123456", "+44", 13));
    }

    @Test
    void phoneNumbersOnly_ShouldBeTrue() {
        assertTrue(phoneValidator.validate("+37061765277"));
    }

    @Test
    void phoneNumbersOnly_ShouldBeFalse() {
        assertFalse(phoneValidator.validate("+37061f765+7"));
    }

    @Test
    void phoneInvalidLength_ShouldBeFalse() {
        assertFalse(phoneValidator.validate("861765277487", "+370", 12));
    }

    @Test
    void phoneNull_ShouldBeFalse() {
        assertFalse(phoneValidator.validate(null));
    }

    @Test
    void phoneEmpty_ShouldBeFalse() {
        assertFalse(phoneValidator.validate(""));
    }

}
