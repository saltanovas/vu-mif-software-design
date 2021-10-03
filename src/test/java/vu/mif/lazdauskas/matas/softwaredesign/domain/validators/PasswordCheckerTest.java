package vu.mif.lazdauskas.matas.softwaredesign.domain.validators;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordCheckerTest {

    PasswordChecker passwordChecker;
    @BeforeEach
    void setUp() {
        passwordChecker = new PasswordChecker();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void passwordNull_ShouldBeFalse () {
        assertFalse(passwordChecker.validate(null, 8, "!@#$%^&*()_+-"));
    }

    @Test
    void passwordSpecialSymbolsNull_ShouldBeFalse () {
        assertFalse(passwordChecker.validate("Abcdef1!", 8, null));
    }

    @Test
    void passwordLength_ShouldBeTrue (){
        assertTrue(passwordChecker.validate("Abcdef1!", 8, "!@#$%^&*()_+-"));
    }

    @Test
    void passwordLength_ShouldBeFalse (){
        assertFalse(passwordChecker.validate("Abc1!", 8, "!@#$%^&*()_+-"));
    }

    @Test
    void passwordSpecialSymbol_ShouldBeFalse () {
        assertFalse(passwordChecker.validate("Abcdef1", 8, "!@#$%^&*()_+-"));
    }

    @Test
    void passwordUppercase_ShouldBeFalse () {
        assertFalse(passwordChecker.validate("abcdef1!", 8, "!@#$%^&*()_+-"));
    }

    @Test
    void passwordUppercase_ShouldBeTrue () {
        assertTrue(passwordChecker.validate("Abcdef1!", 8, "!@#$%^&*()_+-"));
    }

}
