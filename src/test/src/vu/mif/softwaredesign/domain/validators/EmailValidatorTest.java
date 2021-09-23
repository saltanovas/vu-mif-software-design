package vu.mif.softwaredesign.domain.validators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class EmailValidatorTest {

    private EmailValidator emailValidator;

    @BeforeEach
    public void init() {
        emailValidator = new EmailValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "test\"@\"test@gmail.com",
            "test.com@gmail.com",
            "test.\"(),:;<>[]\".null.\"test@\\\\\\ \\\"test\"@gmail.com"
    })
    public void isLocalPartValid(String email) {
        assertTrue(emailValidator.isValid(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "test..test@gmail.com",
            "test@test@gmail.com",
            "a\"b(c)d,e:f;g<h>i[j\\k]l@gmail.com",
            "this is\"not\\allowed@gmail.com"
    })
    public void isLocalPartInvalid(String email) {
        assertFalse(emailValidator.isValid(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "test@gmail.com",
            "test@mif.stud.vu.lt"
    })
    public void hasCorrectDomainAndTLD(String email) {
        assertTrue(emailValidator.isValid(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "test@gmlai.com",
            "test@fmail.com.com",
            "test@test-test.com",
            "test@.com"
    })
    public void hasIncorrectDomainOrTLD(String email) {
        assertFalse(emailValidator.isValid(email));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    public void emptyStringIsNotAllowed(String email) {
        assertFalse(emailValidator.isValid(email));
    }
}
