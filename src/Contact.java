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
        if (personalId.contains("\\d{6}[+-A]\\d{3}[0-9A-FHJ-NPR-Y]")) {
            this.personalId = personalId;
        } else {
            throw new IllegalArgumentException("You did not enter the " +
            "personal ID in correct format!");
        }
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setEmail(String email) {
        this.email = email;
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
