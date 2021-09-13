package vu.mif.softwaredesign.domain.validators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordValidatorTest {

    private PasswordValidator passwordValidator;

    @BeforeEach
    public void init() {
        int minLength = 8;
        String specialChars = "<.,>?/+=_-&^%$#@";

        passwordValidator = new PasswordValidator(minLength, specialChars);
    }

    @Test
    @ValueSource(strings = {
            "AAA:(",
            "aaa:)"
    })
    public void isShorterThan(String password) {
        assertFalse(passwordValidator.isValid(password));
    }

    @Test
    @ValueSource(strings = {
            "123456??",
            "<.?:-nooo"
    })
    public void doesNotHaveUppercase(String password) {
        assertFalse(passwordValidator.isValid(password));
    }

    @Test
    @ValueSource(strings = {
            "Abcd.abcd",
            "<abcS>!{"
    })
    public void hasAtLeastOneSpecialSymbol(String password) {
        assertTrue(passwordValidator.isValid(password));
    }
}
