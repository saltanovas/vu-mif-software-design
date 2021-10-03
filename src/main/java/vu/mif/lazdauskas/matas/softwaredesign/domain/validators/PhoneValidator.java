package vu.mif.lazdauskas.matas.softwaredesign.domain.validators;

import vu.mif.lazdauskas.matas.softwaredesign.domain.enums.InternationalPhoneCodes;

public class PhoneValidator {

    public boolean validate(String phoneNumber, String internationalPrefix, int internationalLength) {
        return InternationalPhoneCodes.getPhoneType(internationalPrefix).isValid(phoneNumber);
    }

    public boolean validate(String phoneNumber) {
        return InternationalPhoneCodes.LITHUANIA.isValid(phoneNumber);
    }
}
