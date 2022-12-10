import java.io.*;
import java.util.ArrayList;
/**
 * Helper class to read and write into the contacts database
 */
public class FileHelper {
    public static void readFile(ContactsManager manager) {
        try {
            File file = new File("contacts.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            while (raf.getFilePointer() < raf.length()) {
                try {
                    String line = raf.readLine();
                    String[] contactArray = line.split(";");
                    Contact contact = new Contact(contactArray[0],
                            contactArray[1], contactArray[2], contactArray[3]);
                    contact.setAddress(contactArray[4]);
                    contact.setEmail(contactArray[5]);
                    manager.addContact(contact);
                } catch (IOException e) {
                    System.out.println("Error reading file");
                }
            }
            raf.close();
        } catch (NullPointerException e) {
            System.out.println("Filepath not found.");
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }

    public static void writeFile(ContactsManager manager) {
        try {
            File file = new File("contacts.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            ArrayList<Contact> contacts = manager.getContacts();
            for (Contact contact : contacts) {
                try {
                    String temp = contact.getPersonalId() + ";" +
                    contact.getFirstName() + ";" +
                    contact.getLastName() + ";" +
                    contact.getPhoneNumber() + ";" +
                    contact.getAddress() + ";" +
                    contact.getEmail() + "\n";
                    
                    raf.writeBytes(temp);
                } catch (IOException e) {
                    System.out.println("Error writing into file");
                }
            }
            raf.close();
        } catch (NullPointerException e) {
            System.out.println("Filepath not found.");
        } catch (IOException e) {
            System.out.println("Error writing into file");
        }
    }
}
