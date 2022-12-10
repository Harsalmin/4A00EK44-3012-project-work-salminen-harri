import java.util.ArrayList;

public class ContactsManager {
    private ArrayList<Contact> contacts;

    public ContactsManager() {
        contacts = new ArrayList<Contact>();
    }

    public String addContact(Contact contact) {
        for (Contact searchContact : contacts) {
            if (searchContact.getPersonalId().equals(contact.getPersonalId())) {
                return "Contact with the same personal ID already exists!";
            }
        }
        contacts.add(contact);
        return "Contact added!";
    }

    public Contact getContact(int index) {
        return contacts.get(index);
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public String deleteContact(Contact contact) {
        contacts.remove(contact);
        return "Contact removed";
    }

    public void displayContact(Contact contact) {
        contact.toString();
    }

    public void displayAllContacts() {
        if (contacts.isEmpty()) {
            throw new IllegalArgumentException(
                "There are no contact information");
        } else {
            int index = 1;
            for (Contact contact : contacts) {
                System.out.println("Contact " + index + ":");
                System.out.println(contact.toString());
                index++;
            }
        }
    }

    public void displayAllContactsShort() {
        if (contacts.isEmpty()) {
            throw new IllegalArgumentException(
                "There are no contact information");
        } else {
            int index = 1;
            for (Contact contact : contacts) {
                System.out.println(index + ". " + contact.toStringShort());
                index++;
            }
        }
    }

    public void updatePersonalId(int index, String newId) {
        contacts.get(index).setPersonalId(newId);
        FileHelper.writeFile(this);
    }

    public void updateFirstName(int index, String newFirstName) {
        contacts.get(index).setFirstName(newFirstName);
        FileHelper.writeFile(this);
    }

    public void updateLastName(int index, String newLastName) {
        contacts.get(index).setLastName(newLastName);
        FileHelper.writeFile(this);
    }

    public void updatePhoneNumber(int index, String newNumber) {
        contacts.get(index).setPhoneNumber(newNumber);
        FileHelper.writeFile(this);
    }

    public void updateAddress(int index, String newAddress) {
        contacts.get(index).setAddress(newAddress);
        FileHelper.writeFile(this);
    }

    public void updateEmail(int index, String newEmail) {
        contacts.get(index).setEmail(newEmail);
        FileHelper.writeFile(this);
    }
}
