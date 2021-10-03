package vu.mif.lazdauskas.matas.softwaredesign.domain.enums;

import java.util.Arrays;

public enum InternationalPhoneCodes {
    LITHUANIA("+370", 12, "8", 9),
    UK("+44", 13, "7", 12);

    private final String internationalPrefix;
    private final int internationalLength;
    private final String nationalPrefix;
    private final int nationalLength;

    InternationalPhoneCodes(String internationalPrefix, int internationalLength, String nationalPrefix, int nationalLength) {
        this.internationalPrefix = internationalPrefix;
        this.internationalLength = internationalLength;
        this.nationalPrefix = nationalPrefix;
        this.nationalLength = nationalLength;
    }

    public static InternationalPhoneCodes getPhoneType(String internationalPrefix) {
        return Arrays.stream(values()).filter(x -> x.internationalPrefix.equals(internationalPrefix)).findFirst().orElse(null);
    }

    public boolean isValid(String phoneNumber) {
        return phoneNumber != null
                && (isInternationalNumber(phoneNumber) || isLocalNumber(phoneNumber));
    }

    private boolean isInternationalNumber(String phoneNumber) {
        return phoneNumber.length() == internationalLength
                && phoneNumber.startsWith(internationalPrefix)
                && phoneNumber.substring(internationalPrefix.length()).chars().allMatch(Character::isDigit);
    }

    private boolean isLocalNumber(String phoneNumber) {
        return phoneNumber.length() == nationalLength
                && phoneNumber.startsWith(nationalPrefix)
                && phoneNumber.substring(nationalPrefix.length()).chars().allMatch(Character::isDigit);
    }
}
