import java.io.*;
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
        } catch (IOException e) {
            System.out.println("File not found.");
        } 
    }

    public static void writeFile() {

    }
}
