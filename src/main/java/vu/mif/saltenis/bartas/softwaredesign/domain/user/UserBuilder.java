package vu.mif.saltenis.bartas.softwaredesign.domain.user;

public class UserBuilder {
    private final String firstName;
    private final String lastName;
    private String phoneNumber;
    private String email;
    private String address;
    private String password;
    private UserValidator userValidator;

    public UserBuilder(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User build() {
        User user = new User(this);

        if (userValidator != null) {
            userValidator.isValid(user);
        }

        return user;
    }

    public UserBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }
}
