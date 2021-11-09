package vu.mif.saltenis.bartas.softwaredesign.domain.exception;

public enum Error {
    INVALID_PASSWORD(1000, "InvalidPassword", "Password is not valid."),
    INVALID_EMAIL(1001, "InvalidEmail", "Email is not valid."),
    INVALID_PHONE_NUMBER(1002, "InvalidPhoneNumber", "Phone number is not valid.");

    private final int errorCode;
    private final String name;
    private final String description;

    Error(int errorCode, String name, String description) {
        this.errorCode = errorCode;
        this.name = name;
        this.description = description;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
