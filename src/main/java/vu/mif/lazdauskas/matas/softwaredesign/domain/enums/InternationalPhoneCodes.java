package vu.mif.lazdauskas.matas.softwaredesign.domain.enums;

import java.util.Arrays;

public enum InternationalPhoneCodes {
    LT("+370", 12) {
        @Override
        public boolean isValid(String phoneNumber) {
            return phoneNumber.startsWith(getInternationalPrefix())
                    ? super.isValid(phoneNumber)
                    : super.isValid(convertToInternationalNumber(phoneNumber));
        }

        private String convertToInternationalNumber(String phoneNumber) {
            return phoneNumber != null && phoneNumber.startsWith("8")
                    ? getInternationalPrefix() + phoneNumber.substring(1)
                    : null;
        }
    },
    UK("+44", 13);

    private final String internationalPrefix;
    private final int internationalPhoneLength;

    InternationalPhoneCodes(String internationalPrefix, int internationalPhoneLength) {
        this.internationalPrefix = internationalPrefix;
        this.internationalPhoneLength = internationalPhoneLength;
    }

    public static InternationalPhoneCodes findFirstOrNull(String internationalPrefix) {
        return Arrays.stream(values())
                .filter(x -> x.internationalPrefix.equals(internationalPrefix))
                .findFirst()
                .orElse(null);
    }

    public boolean isValid(String internationalPhoneNumber) {
        return internationalPhoneNumber != null
                && internationalPhoneNumber.length() == internationalPhoneLength
                && internationalPhoneNumber.startsWith(internationalPrefix)
                && internationalPhoneNumber.substring(1).chars().allMatch(Character::isDigit);
    }

    public String getInternationalPrefix() {
        return internationalPrefix;
    }
}
