package vu.mif.lazdauskas.matas.softwaredesign.domain.validators;

import vu.mif.lazdauskas.matas.softwaredesign.domain.enums.InternationalPhoneCodes;

public class PhoneValidator {

    public boolean validate(String phoneNumber, String internationalPrefix, int internationalLength) {
        final var internationalPhoneCode = InternationalPhoneCodes.findFirstOrNull(internationalPrefix);

        return internationalPhoneCode != null && internationalPhoneCode.isValid(phoneNumber);
    }

    public boolean validate(String phoneNumber) {
        return InternationalPhoneCodes.LT.isValid(phoneNumber);
    }
}
