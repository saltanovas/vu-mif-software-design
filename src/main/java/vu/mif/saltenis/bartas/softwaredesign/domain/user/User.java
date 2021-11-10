package vu.mif.saltenis.bartas.softwaredesign.domain.user;

import vu.mif.saltenis.bartas.softwaredesign.share.BaseEntity;

public class User extends BaseEntity {
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
    private final String email;
    private final String address;
    private final String password;

    public User(UserBuilder userBuilder) {
        this.firstName = userBuilder.getFirstName();
        this.lastName = userBuilder.getLastName();
        this.phoneNumber = userBuilder.getPhoneNumber();
        this.email = userBuilder.getEmail();
        this.address = userBuilder.getAddress();
        this.password = userBuilder.getPassword();
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

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s %s\n",
                getId(), firstName, lastName, phoneNumber, email, address, password);
    }
}
