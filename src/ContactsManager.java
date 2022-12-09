import java.util.ArrayList;

public class ContactsManager {
    private ArrayList<Contact> contacts;

    public ContactsManager() {
        contacts = new ArrayList<Contact>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void deleteContact() {

    }

    public void readContact() {
        
    }

    public void displayAllContacts() {
        if (contacts.isEmpty()) {
            throw new IllegalArgumentException(
                "There are no contact information");
        } else {
            for (Contact contact : contacts) {
                System.out.println(contact.toString());
            }
        }
    }

    public void updatePersonalId() {

    }

    public void updateFirstName() {

    }

    public void updateLastName() {

    }

    public void updatePhoneNumber() {

    }

    public void updateAddress() {

    }

    public void updateEmail() {

    }
}
