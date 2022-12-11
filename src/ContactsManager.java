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
        FileHelper.writeFile(this);
        return "Contact added succesfully!";
    }

    public Contact getContact(int index) {
        return contacts.get(index);
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public String deleteContact(Contact contact) {
        contacts.remove(contact);
        FileHelper.writeFile(this);
        return "Contact removed\n";
    }

    public String displayAllContacts() {
        if (contacts.isEmpty()) {
            throw new IllegalArgumentException(
                "There are no contact information");
        } else {
            int index = 1;
            String result = "";
            for (Contact contact : contacts) {
                result += "Contact " + index + ":\n";
                result += contact.toString() + "\n";
                index++;
            }
            return result;
        }
    }

    public String displayAllContactsShort() {
        if (contacts.isEmpty()) {
            throw new IllegalArgumentException(
                "There are no contact information");
        } else {
            int index = 1;
            String result = "";
            for (Contact contact : contacts) {
                result += index + ". " + contact.toStringShort() + "\n";
                index++;
            }
            return result;
        }
    }

    public ArrayList<Contact> searchKeyword(String keyword) {
        keyword.toLowerCase();
        ArrayList<Contact> results = new ArrayList<Contact>();
        for (Contact contact : contacts) {
            String temp = FileHelper.contactToString(contact).toLowerCase();
            if (temp.contains(keyword)) {
                results.add(contact);
            }
        }
        return results;
    }

    public String updatePersonalId(int index, String newId) {
        try {
            contacts.get(index).setPersonalId(newId);
            FileHelper.writeFile(this);
            return "Update succesful!";
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(iae.getMessage());
        }
    }

    public String updateFirstName(int index, String newFirstName) {
        try {
            contacts.get(index).setFirstName(newFirstName);
            FileHelper.writeFile(this);
            return "Update succesful!";
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(iae.getMessage());
        } 
    }

    public String updateLastName(int index, String newLastName) {
        try {
            contacts.get(index).setLastName(newLastName);
            FileHelper.writeFile(this);
            return "Update succesful!";
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(iae.getMessage());
        }
    }

    public String updatePhoneNumber(int index, String newNumber) {
        try {
            contacts.get(index).setPhoneNumber(newNumber);
            FileHelper.writeFile(this);
            return "Update succesful!";
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(iae.getMessage());
        }
    }

    public String updateAddress(int index, String newAddress) {
        try {
            contacts.get(index).setAddress(newAddress);
            FileHelper.writeFile(this);
            return "Update succesful!";
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(iae.getMessage());
        }
    }

    public String updateEmail(int index, String newEmail) {
        try {
            contacts.get(index).setEmail(newEmail);
            FileHelper.writeFile(this);
            return "Update succesful!";
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(iae.getMessage());
        }
    }
}
