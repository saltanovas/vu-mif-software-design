package vu.mif.saltenis.bartas.softwaredesign.domain.user;

import org.junit.jupiter.api.Test;
import vu.mif.saltenis.bartas.softwaredesign.domain.exception.Error;
import vu.mif.saltenis.bartas.softwaredesign.domain.exception.ErrorException;

import static org.junit.jupiter.api.Assertions.*;

class UserBuilderTest {

    @Test
    void whenUserIsValid_ShouldNotThrowException() {
        User user = new UserBuilder("FirstName", "LastName")
                .setPassword("123Aa?")
                .setEmail("test@test.com")
                .setPhoneNumber("+37064333333")
                .setAddress("addr")
                .setValidator(new UserValidator(3))
                .build();

        assertDoesNotThrow(() -> user);
    }

    @Test
    public void whenAnyPropertyIsNull_thenThrowException() {
        RuntimeException exception = assertThrows(ErrorException.class, () ->
                new UserBuilder(null, "LastName")
                        .setPassword("123Aa?")
                        .setEmail("test@test.com")
                        .setPhoneNumber("+37064333333")
                        .setAddress("addr")
                        .setValidator(new UserValidator(3))
                        .build());

        String expectedMessage = Error.PROPERTY_IS_NULL.getDescription();
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void whenPasswordInvalid_thenThrowException() {
        RuntimeException exception = assertThrows(ErrorException.class, () ->
                new UserBuilder("FirstName", "LastName")
                        .setPassword("123Aa?")
                        .setEmail("test@test.com")
                        .setPhoneNumber("+37064333333")
                        .setAddress("addr")
                        .setValidator(new UserValidator(10))
                        .build());

        String expectedMessage = Error.INVALID_PASSWORD.getDescription();
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void whenEmailInvalid_thenThrowException() {
        RuntimeException exception = assertThrows(ErrorException.class, () ->
                new UserBuilder("FirstName", "LastName")
                        .setPassword("123Aa?")
                        .setEmail("test")
                        .setPhoneNumber("+37064333333")
                        .setAddress("addr")
                        .setValidator(new UserValidator(2))
                        .build());

        String expectedMessage = Error.INVALID_EMAIL.getDescription();
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void whenPhoneNumberInvalid_thenThrowException() {
        RuntimeException exception = assertThrows(ErrorException.class, () ->
                new UserBuilder("FirstName", "LastName")
                        .setPassword("123Aa?")
                        .setEmail("test@test.com")
                        .setPhoneNumber("+37064")
                        .setAddress("addr")
                        .setValidator(new UserValidator(2))
                        .build());

        String expectedMessage = Error.INVALID_PHONE_NUMBER.getDescription();
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}
