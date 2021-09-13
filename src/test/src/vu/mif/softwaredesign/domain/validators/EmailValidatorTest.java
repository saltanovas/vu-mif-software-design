package vu.mif.softwaredesign.domain.validators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class EmailValidatorTest {

    private EmailValidator emailValidator;

    @BeforeEach
    public void init() {
        emailValidator = new EmailValidator();
    }

    @Test
    @ValueSource(strings = {
            "test\"@\"test@gmail.com",
            "test.com@gmail.com",
            "test.\"(),:;<>[]\".null.\"test@\\\\\\ \\\"test\"@gmail.com"
    })
    public void isLocalPartValid(String email) {
        assertTrue(emailValidator.isValid(email));
    }

    @Test
    @ValueSource(strings = {
            "test..test@gmail.com",
            "test@test@gmail.com",
            "a\"b(c)d,e:f;g<h>i[j\\k]l@gmail.com",
            "this is\"not\\allowed@gmail.com"
    })
    public void isLocalPartInvalid(String email) {
        assertFalse(emailValidator.isValid(email));
    }

    @Test
    @ValueSource(strings = {
            "test@yahoo.com",
            "test@gmail.com"
    })
    public void hasCorrectDomainAndTLD(String email) {
        assertTrue(emailValidator.isValid(email));
    }

    @Test
    @ValueSource(strings = {
            "test@gmlai.com",
            "test@fmail.com.com",
            "test@test-test.com",
            "test@.com"})
    public void hasIncorrectDomainOrTLD(String email) {
        assertFalse(emailValidator.isValid(email));
    }
}
