package vu.mif.lazdauskas.matas.softwaredesign.domain.validators;

import vu.mif.lazdauskas.matas.softwaredesign.domain.helpers.StringUtil;

public class PasswordChecker {

    private final StringUtil stringUtil;

    public PasswordChecker() {
        stringUtil = new StringUtil();
    }

    public boolean validate(String password, int passwordMinLength, String specialChars) {
        return password != null
                && specialChars != null
                && password.length() >= passwordMinLength
                && !password.equals(password.toLowerCase())
                && stringUtil.firstInSet(password, specialChars) != -1;
    }
}
