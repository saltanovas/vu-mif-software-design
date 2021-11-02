package vu.mif.lazdauskas.matas.softwaredesign.domain.validators;

import vu.mif.lazdauskas.matas.softwaredesign.domain.helpers.StringUtil;

public class PasswordChecker {

    private final StringUtil stringUtil;

    public PasswordChecker() {
        stringUtil = new StringUtil();
    }

    public boolean validate(String password, int passwordMinLength, String allowedSpecialChars) {
        return password != null
                && password.length() >= passwordMinLength
                && !password.equals(password.toLowerCase())
                && (allowedSpecialChars == null
                ? password.chars().allMatch(Character::isLetterOrDigit)
                : stringUtil.firstInSet(password, allowedSpecialChars) != -1);
    }
}
