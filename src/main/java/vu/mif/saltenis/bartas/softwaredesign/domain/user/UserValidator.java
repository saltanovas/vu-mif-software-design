package vu.mif.saltenis.bartas.softwaredesign.domain.user;

import vu.mif.saltenis.bartas.softwaredesign.domain.exception.Error;
import vu.mif.saltenis.bartas.softwaredesign.domain.exception.ErrorException;
import vu.mif.saltenis.bartas.softwaredesign.domain.validators.EmailChecker;
import vu.mif.saltenis.bartas.softwaredesign.domain.validators.PasswordChecker;
import vu.mif.saltenis.bartas.softwaredesign.domain.validators.PhoneChecker;

import java.lang.reflect.Field;

public class UserValidator {
    private final EmailChecker emailValidator;
    private final PasswordChecker passwordValidator;
    private final PhoneChecker phoneNumberValidator;

    private final int minPasswordLength;

    public UserValidator(int minPasswordLength) {
        this.emailValidator = new EmailChecker();
        this.passwordValidator = new PasswordChecker();
        this.phoneNumberValidator = new PhoneChecker();

        this.minPasswordLength = minPasswordLength;
    }

    public void isValid(User user) {
        try {
            for (Field f : user.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (f.get(user) == null) throw new ErrorException(Error.PROPERTY_IS_NULL);
            }
        } catch (IllegalAccessException ignored) { }

        if (!validatePassword(user.getPassword())) {
            throw new ErrorException(Error.INVALID_PASSWORD);
        }

        if (!validateEmail(user.getEmail())) {
            throw new ErrorException(Error.INVALID_EMAIL);
        }

        if (!validatePhoneNumber(user.getPhoneNumber())) {
            throw new ErrorException(Error.INVALID_PHONE_NUMBER);
        }
    }

    private boolean validatePassword(String password) {
        return passwordValidator.lengthRequirement(password, minPasswordLength)
                && passwordValidator.specialSymbolsRequirement(password)
                && passwordValidator.uppercaseRequirement(password);
    }

    private boolean validateEmail(String email) {
        return emailValidator.validEmailRequirement(email)
                && emailValidator.validEmailSymbolsRequirement(email)
                && emailValidator.validDomainRequirement(email);
    }

    //according this perfect library, only LT numbers are valid <3
    private boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumberValidator.validNumberRequirement(phoneNumber);
    }
}
