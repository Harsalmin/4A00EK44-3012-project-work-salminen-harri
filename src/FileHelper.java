import java.io.*;
import java.util.ArrayList;
/**
 * Helper class to read and write into the contacts database, contained in a 
 * text file named "contacts.txt".
 * 
 * @author Harri Salminen
 */
public class FileHelper {
    /**
     * 
     * @param manager
     */
    public static void readFile(ContactsManager manager) {
        try {
            File file = new File("contacts.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            while (raf.getFilePointer() < raf.length()) {
                readContact(raf, manager);
            }
            raf.close();
        } catch (NullPointerException e) {
            System.out.println("Filepath not found.");
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }

    /**
     * 
     * @param raf
     * @param mngr
     */
    public static void readContact(RandomAccessFile raf, ContactsManager mngr) {
        try {
            String line = raf.readLine();
            String[] contactArray = line.split(";");
            Contact contact = new Contact();
            contact.setPersonalId(contactArray[0]);
            contact.setFirstName(contactArray[1]);
            contact.setLastName(contactArray[2]);
            contact.setPhoneNumber(contactArray[3]);
            contact.setAddress(contactArray[4]);
            contact.setEmail(contactArray[5]);
            mngr.addContact(contact);
        } catch (IOException e) {
            System.out.println("Error reading file");
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(iae.getMessage());
        }
    }

    /**
     * 
     * @param manager
     */
    public static void writeFile(ContactsManager manager) {
        try {
            File file = new File("contacts.txt");
            file.delete();
            if (!file.exists()) {
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rws");

            File tmpfile = new File("temp.txt");
            RandomAccessFile tempraf = new RandomAccessFile(tmpfile, "rws");

            ArrayList<Contact> contacts = manager.getContacts();
            for (Contact contact : contacts) {
                try {
                    tempraf.writeBytes(contactToString(contact));
                    tempraf.writeBytes(System.lineSeparator());
                } catch (IOException e) {
                    System.out.println("Error writing into file");
                }
            }
            copyFile(tempraf, raf);
            raf.close();
            tempraf.close();
            tmpfile.delete();
        } catch (NullPointerException e) {
            System.out.println("Filepath not found.");
        } catch (IOException e) {
            System.out.println("Error writing into file");
        }
    }

    /**
     * 
     * @param contact
     * @return
     */
    public static String contactToString(Contact contact) {
        return contact.getPersonalId() + ";" +
            contact.getFirstName() + ";" +
            contact.getLastName() + ";" +
            contact.getPhoneNumber() + ";" +
            contact.getAddress() + ";" +
            contact.getEmail();
    }

    /**
     * 
     * @param original
     * @param copy
     */
    public static void copyFile(
        RandomAccessFile original, RandomAccessFile copy) {
        try {
            original.seek(0);
            copy.seek(0);

            while (original.getFilePointer() < original.length()) {
                copy.writeBytes(original.readLine());
                copy.writeBytes(System.lineSeparator());
            }

        } catch (IOException e) {
            System.out.println("Error writing into file");
        }
        
    }
}
