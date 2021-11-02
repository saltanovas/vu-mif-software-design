package vu.mif.lazdauskas.matas.softwaredesign.domain.validators;

import vu.mif.lazdauskas.matas.softwaredesign.domain.helpers.StringUtil;

public class EmailValidator {

    private static final String localPartValidSpecialChars = "!#$%&'*+-/=?^_`{|}~.";
    private static final String domainPartValidSpecialChars = ".-";
    private final StringUtil stringUtil;

    public EmailValidator() {
        stringUtil = new StringUtil();
    }

    public boolean validate(String email) {
        if(email == null || !hasOnlyOneAtSymbol(email)) {
            return false;
        }

        final var emailParts = email.split("@");
        final var localPart = emailParts[0];
        final var domainPart = emailParts[1];

        return isLocalPartValid(localPart) && isDomainPartValid(domainPart);
    }

    private boolean hasOnlyOneAtSymbol(String email) {
        return email.chars().filter(ch -> ch == '@').count() == 1;
    }

    private boolean isDomainPartValid(String domainPart) {
        return domainPart.length() > 3 && domainPart.length() <= 255
                && isTLDCorrect(domainPart)
                && stringUtil.charNotFirstOrLastOrConsecutive(domainPart, ".")
                && stringUtil.charNotFirstOrLastOrConsecutive(domainPart, "-")
                && stringUtil.isAlphanumericOrSpecial(domainPart, domainPartValidSpecialChars);
    }

    private boolean isLocalPartValid(String localPart) {
        return localPart.length() <= 64
                && stringUtil.charNotFirstOrLastOrConsecutive(localPart, ".")
                && stringUtil.isAlphanumericOrSpecial(localPart, localPartValidSpecialChars);
    }

    private boolean isTLDCorrect(String domainPart) {
        return domainPart.contains(".") && domainPart.length() - domainPart.lastIndexOf('.') >= 3;
    }
}
