import java.util.ArrayList;

/**
 * This is the main class for handling the CRUD operations on contact 
 * database. The database is created in the form of an ArrayList, containing
 * Contact objects. An object of ContactsManager is created in ContactsApp 
 * class for this purpose. The methods of this class handle the CRUD 
 * operations and evertyhing realated to them.
 * 
 * @author Harri Salminen
 */
public class ContactsManager {
    private ArrayList<Contact> contacts;

    /**
     * Default constructor, that just simply creates new ArrayList to store
     * the contacts in. Even though personal ID, first name, last name and 
     * phone number are all mandatory, they are not required on creation, 
     * since they will be evaluated elsewhere first before the Contact object
     * is actually added to the ArrayList.
     */
    public ContactsManager() {
        contacts = new ArrayList<Contact>();
    }

    /**
     * Method for adding a new contact. First searches the ArrayList of
     * contacts if any of them have the same personal ID as the new contact, as
     * personal IDs are unique and two people cannot have the same personal ID.
     * If found, returns with a message telling about that without actually
     * adding the new contact. Else adds the contact, writes the new contact
     * into the contacts.txt database file, and return message of successful
     * add.
     * 
     * @param contact The new contact object to be added.
     * 
     * @throws IllegalArgumentException if filw writing fails due to IO failure
     * or pathname error.
     * 
     * @return The String message telling about either failure or success of
     * adding the new contact.
     */
    public String addContact(Contact contact) {
        /*
         * Go through the ArrayList of contacts, looking for matching personal 
         * IDs.
         */
        for (Contact searchContact : contacts) {
            if (searchContact.getPersonalId().equals(contact.getPersonalId())) {
                return "Contact with the same personal ID already exists!";
            }
        }
        contacts.add(contact);
        try {
            FileHelper.writeFile(this);
        } catch (Exception e) {
            throw new IllegalArgumentException(
                "Error when writing into file.");
        }
        return "Contact added succesfully!";
    }

    /**
     * Method to return a Contact object specified by its index in the 
     * ArrayList.
     * 
     * @param index Index of the contact searched for.
     * 
     * @return The Contact object with the index.
     */
    public Contact getContact(int index) {
        return contacts.get(index);
    }

    /**
     * Method to return the ArrayList of contacts.
     * 
     * @return ArrayList containing all the Contact objects.
     */
    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    /**
     * Method for deleting Contact object on the ArrayList. Returns with
     * success message if deletion was successful. Throws exceptions when file
     * writing fails, which causes disconnect between ArrayList and contacts.txt
     * database, or the Contact object is not found on the ArrayList.
     * 
     * @param contact The Contact object to be deleted.
     * 
     * @throws IllegalArgumentException if file writing fails after deletion.
     * 
     * @throws NullPointerException if the Contact object is not found on the 
     * ArrayList.
     * 
     * @return Message telling of succesful removal.
     */
    public String deleteContact(Contact contact) {
        try {
            contacts.remove(contact);
            try {
                FileHelper.writeFile(this);
            } catch (Exception e) {
                throw new IllegalArgumentException(
                    "Error when writing into file.");
            }
            
            return "Contact removed.";
        } catch (NullPointerException npe) {
            throw new NullPointerException(
                "Could not find contact to be removed.");
        }
    }
        
    /**
     * Method to display all contact of the ArrayList in a specific manner,
     * utilizing each Contact object toString() method.
     * 
     * @throws IllegalArgumentException if the ArrayList is empty.
     * 
     * @return One big String with all the contact information in it.
     */
    public String displayAllContacts() {
        if (contacts.isEmpty()) {
            throw new IllegalArgumentException(
                "There are no contact information\n");
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

    /**
     * Method to display the short version of contact info, only displaying
     * personal ID, first name and last name of each contact. It utilizes
     * Contact classes toStringShort() to fetch the necessary info as String,
     * then combines them all into one big String, which is then returned.
     * 
     * @throws IllegalArgumentException if ArrayList of contact information is 
     * empty.
     * 
     * @return One big String containing the shortened info of contacts.
     */
    public String displayAllContactsShort() {
        if (contacts.isEmpty()) {
            throw new IllegalArgumentException(
                "There are no contact information.\n");
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

    /**
     * Method to serach for a Contact object with a specific keyword. The idea 
     * is to utilize the same toStringWriteFile() method of Contact class as 
     * with when writing Contact objects into file, and search for the keyword 
     * within those long contact info Strings. If keyword is fount on object, 
     * the object is put into a new ArrayList containing the results.
     * 
     * @param keyword Keyword searched for.
     * 
     * @return ArrayList of resulting Contact objects, if any.
     */
    public ArrayList<Contact> searchKeyword(String keyword) {
        keyword.toLowerCase();
        ArrayList<Contact> results = new ArrayList<Contact>();
        for (Contact contact : contacts) {
            String temp = contact.ToStringWriteFile().toLowerCase();
            if (temp.contains(keyword)) {
                results.add(contact);
            }
        }
        return results;
    }

    /**
     * Method for updating personal ID of an Contact object. Takes the index of
     * the Contact object in the ArrayList to be updated, as well as the new
     * personal ID. Writes the update into file if validation goes through and
     * returns a success message, else the set method at Contact class throws
     * an exception, which is caught here and rethrown.
     * 
     * @param index Index of the Contact object in the ArrayList.
     * 
     * @param newId User-inputted new personal Id to be validated and set as
     * new personal ID.
     * 
     * @throws IllegalArgumentException if validation of new personal ID fails 
     * or file writing fails after update.
     * 
     * @return Succes message for update.
     */
    public String updatePersonalId(int index, String newId) {
        try {
            contacts.get(index).setPersonalId(newId);
            try {
                FileHelper.writeFile(this);
            } catch (Exception e) {
                throw new IllegalArgumentException(
                        "Error when writing into file.");
            }
            return "Update succesful!";
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(iae.getMessage());
        }
    }

    /**
     * Method for updating first name of an Contact object. Takes the index of
     * the Contact object in the ArrayList to be updated, as well as the new
     * first name. Writes the update into file if validation goes through and
     * returns a success message, else the set method at Contact class throws
     * an exception, which is caught here and rethrown.
     * 
     * @param index Index of the Contact object in the ArrayList.
     * 
     * @param newFirstName User-inputted new first name to be validated and 
     * set as new first name.
     * 
     * @throws IllegalArgumentException if validation of new personal ID fails
     * or file writing fails after update.
     * 
     * @return Succes message for update.
     */
    public String updateFirstName(int index, String newFirstName) {
        try {
            contacts.get(index).setFirstName(newFirstName);
            try {
                FileHelper.writeFile(this);
            } catch (Exception e) {
                throw new IllegalArgumentException(
                        "Error when writing into file.");
            }
            return "Update succesful!";
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(iae.getMessage());
        } 
    }

    /**
     * Method for updating last name of an Contact object. Takes the index of
     * the Contact object in the ArrayList to be updated, as well as the new
     * last name. Writes the update into file if validation goes through and
     * returns a success message, else the set method at Contact class throws
     * an exception, which is caught here and rethrown.
     * 
     * @param index Index of the Contact object in the ArrayList.
     * 
     * @param newLastName User-inputted new last name to be validated and set 
     * as new last name.
     * 
     * @throws IllegalArgumentException if validation of new personal ID fails
     * or file writing fails after update.
     * 
     * @return Succes message for update.
     */
    public String updateLastName(int index, String newLastName) {
        try {
            contacts.get(index).setLastName(newLastName);
            try {
                FileHelper.writeFile(this);
            } catch (Exception e) {
                throw new IllegalArgumentException(
                        "Error when writing into file.");
            }
            return "Update succesful!";
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(iae.getMessage());
        }
    }

    /**
     * Method for updating phone number of an Contact object. Takes the index
     * of the Contact object in the ArrayList to be updated, as well as the new
     * phone number. Writes the update into file if validation goes through and
     * returns a success message, else the set method at Contact class throws
     * an exception, which is caught here and rethrown.
     * 
     * @param index Index of the Contact object in the ArrayList.
     * 
     * @param newNumber User-inputted new phone number to be validated and set 
     * as the new phone number.
     * 
     * @throws IllegalArgumentException if validation of new personal ID fails
     * or file writing fails after update.
     * 
     * @return Succes message for update.
     */
    public String updatePhoneNumber(int index, String newNumber) {
        try {
            contacts.get(index).setPhoneNumber(newNumber);
            try {
                FileHelper.writeFile(this);
            } catch (Exception e) {
                throw new IllegalArgumentException(
                    "Error when writing into file.");
            }
            return "Update succesful!";
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(iae.getMessage());
        }
    }

    /**
     * Method for updating address of an Contact object. Takes the index of the
     * Contact object in the ArrayList to be updated, as well as the new
     * address. Writes the update into file if validation goes through and
     * returns a success message, else the set method at Contact class throws
     * an exception, which is caught here and rethrown.
     * 
     * @param index Index of the Contact object in the ArrayList.
     * 
     * @param newAddress User-inputted new address to be validated and set as 
     * the new address.
     * 
     * @throws IllegalArgumentException if validation of new personal ID fails
     * or file writing fails after update.
     * 
     * @return Succes message for update.
     */
    public String updateAddress(int index, String newAddress) {
        try {
            contacts.get(index).setAddress(newAddress);
            try {
                FileHelper.writeFile(this);
            } catch (Exception e) {
                throw new IllegalArgumentException(
                        "Error when writing into file.");
            }
            return "Update succesful!";
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(iae.getMessage());
        }
    }

    /**
     * Method for updating e-mail of an Contact object. Takes the index of
     * the Contact object in the ArrayList to be updated, as well as the new
     * e-mail. Writes the update into file if validation goes through and
     * returns a success message, else the set method at Contact class throws
     * an exception, which is caught here and rethrown.
     * 
     * @param index Index of the Contact object in the ArrayList.
     * 
     * @param newEmail User-inputted new e-mail address to be validated and set 
     * as the new e-mail.
     * 
     * @throws IllegalArgumentException if validation of new personal ID fails
     * or file writing fails after update.
     * 
     * @return Succes message for update.
     */
    public String updateEmail(int index, String newEmail) {
        try {
            contacts.get(index).setEmail(newEmail);
            try {
                FileHelper.writeFile(this);
            } catch (Exception e) {
                throw new IllegalArgumentException(
                        "Error when writing into file.");
            }
            return "Update succesful!";
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(iae.getMessage());
        }
    }
}
