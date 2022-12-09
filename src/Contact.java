public class Contact {
    private String personalId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String email;

    public Contact(String personalId, String firstName, String lastName, String phoneNumber) {
        setPersonalId(personalId);
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
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
}
