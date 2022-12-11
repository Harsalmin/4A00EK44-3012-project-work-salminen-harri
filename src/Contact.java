public class Contact {
    private String personalId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String email;

    public Contact() {
    }

    public void setPersonalId(String personalId) {
        if (personalId.matches("\\d{6}[+-A]\\d{3}[0-9A-FHJ-NPR-Y]")) {
            this.personalId = personalId;
        } else {
            throw new IllegalArgumentException(
                "You did not enter the personal ID in correct format!");
        }
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setFirstName(String firstName) {
        if (firstName.matches("^[A-ZÅÄÖ][\\w-åäö]+")) {
            this.firstName = firstName;
        } else {
            throw new IllegalArgumentException(
                "You did not enter the first name in correct format!");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        if (lastName.matches("^[A-ZÅÄÖ][\\w-åäö]+")) {
            this.lastName = lastName;
        } else {
            throw new IllegalArgumentException(
                "You did not enter the last name in correct format!");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("^+358\\d{5}\\d+")) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new IllegalArgumentException(
                "You did not enter the phone number in correct format!");
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setAddress(String address) {
        if (address.matches(
            "^[A-ZÅÄÖ][\\w\\s-åäö]+ \\d+") || address.equals(null)) {
            this.address = address;
        } else {
            throw new IllegalArgumentException(
                "You did not enter the address in correct format!");
        }
    }

    public String getAddress() {
        return address;
    }

    public void setEmail(String email) {
        if (email.matches("\\S+@\\S+.\\S+") || email.equals(null)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException(
                "You did not enter the email in correct format!");
        }
    }

    public String getEmail() {
        return email;
    }

    public String toStringShort() {
        return "ID: " + personalId +
        ", " + firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Personal ID: " + personalId +
        "\nFirst name: " + firstName +
        "\nLast name: " + lastName +
        "\nPhone number: " + phoneNumber +
        "\nAddress: " + address +
        "\nE-mail: " + email + "\n";
    }
}
