package vu.mif.saltenis.bartas.softwaredesign.domain.exception;

public class ErrorException extends RuntimeException {
    private final int statusCode;
    private final String name;
    private final String description;

    public ErrorException(Error error) {
        super(error.getDescription());

        this.statusCode = error.getErrorCode();
        this.name = error.getName();
        this.description = error.getDescription();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
