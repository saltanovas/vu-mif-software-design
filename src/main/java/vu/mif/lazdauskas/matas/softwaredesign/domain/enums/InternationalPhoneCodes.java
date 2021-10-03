package vu.mif.lazdauskas.matas.softwaredesign.domain.enums;

import java.util.Arrays;

public enum InternationalPhoneCodes {
    LITHUANIA("+370", 12) {
        @Override
        public boolean isValid(String phoneNumber) {
            return super.isValid(phoneNumber) || super.isValid(convertToInternationalNumber(phoneNumber));
        }

        private String convertToInternationalNumber(String phoneNumber) {
            return phoneNumber != null && phoneNumber.startsWith("8")
                    ? getInternationalPrefix() + phoneNumber.substring(1)
                    : null;
        }
    },
    UK("+44", 13);

    private final String internationalPrefix;
    private final int internationalLength;

    InternationalPhoneCodes(String internationalPrefix, int internationalLength) {
        this.internationalPrefix = internationalPrefix;
        this.internationalLength = internationalLength;
    }

    public static InternationalPhoneCodes findFirstOrNull(String internationalPrefix) {
        return Arrays.stream(values())
                .filter(x -> x.internationalPrefix.equals(internationalPrefix))
                .findFirst()
                .orElse(null);
    }

    public boolean isValid(String phoneNumber) {
        return phoneNumber != null && isInternationalNumber(phoneNumber);
    }

    private boolean isInternationalNumber(String phoneNumber) {
        return phoneNumber.length() == internationalLength
                && phoneNumber.startsWith(internationalPrefix)
                && phoneNumber.substring(1).chars().allMatch(Character::isDigit);
    }

    public String getInternationalPrefix() {
        return internationalPrefix;
    }
}
