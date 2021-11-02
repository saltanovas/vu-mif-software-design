package vu.mif.lazdauskas.matas.softwaredesign.domain.validators;

import vu.mif.lazdauskas.matas.softwaredesign.domain.enums.InternationalPhoneCodes;

import java.util.Arrays;

public class PhoneValidator {

    public boolean validate(String phoneNumber, String internationalPrefix, int internationalNumberLength) {
        final var internationalPhoneCode = InternationalPhoneCodes.findFirstOrNull(internationalPrefix);

        return internationalPhoneCode != null && internationalPhoneCode.isValid(phoneNumber);
    }

    public boolean validate(String internationalPhoneNumber) {
        if(internationalPhoneNumber == null) {
            return false;
        }

        final var internationalPhoneCode = Arrays.stream(InternationalPhoneCodes.values())
                .filter(x -> internationalPhoneNumber.startsWith(x.getInternationalPrefix()))
                .findFirst()
                .orElse(null);

        return internationalPhoneCode != null && internationalPhoneCode.isValid(internationalPhoneNumber);
    }
}
