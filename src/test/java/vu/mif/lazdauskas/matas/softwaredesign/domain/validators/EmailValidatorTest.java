package vu.mif.lazdauskas.matas.softwaredesign.domain.validators;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    EmailValidator emailValidator;
    @BeforeEach
    void setUp() {
        emailValidator = new EmailValidator();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void emailEmptyLine_ShouldBeFalse (){
        assertFalse(emailValidator.validate(""));
    }

    @Test
    void emailNull_ShouldBeFalse (){
        assertFalse(emailValidator.validate(null));
    }

    @Test
    void emailNoAtSign_ShouldBeFalse (){
        assertFalse(emailValidator.validate("outlook.com"));
    }

    @Test
    void emailAtSign_ShouldBeTrue (){
        assertTrue(emailValidator.validate("abc@outlook.com"));
    }

    @Test
    void emailNoName_ShouldBeFalse (){
        assertFalse(emailValidator.validate("@outlook.com"));
    }

    @Test
    void emailValidName_ShouldBeTrue (){
        assertTrue(emailValidator.validate("abc.cba@outlook.com"));
    }

    @Test
    void emailInvalidSymbol_ShouldBeFalse (){
        assertFalse(emailValidator.validate("abc)cba@outlook.com"));
    }

    @Test
    void emailInvalidDomain_ShouldBeFalse (){
        assertFalse(emailValidator.validate("abc@mail"));
    }

    @Test
    void emailValidDomain_ShouldBeTrue (){
        assertTrue(emailValidator.validate("abc@mif.stud.vu.lt"));
    }

}
