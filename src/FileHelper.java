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
     * This method read the creates a new File object with contacts.txt as file
     * name. Then it creates a new RandomAccesFile object using the File object,
     * so the file can be read from.
     * 
     * @param manager ContactsManager object created in ContactsApp class, 
     * where Contact objects will be stored after file reading
     * and object creation.
     * 
     * @throws NullPointerException if the pathname is not given as argument
     * during file object creation.
     * 
     * @throws IOException if reading of the file fails for any reason.
     */
    public static void readFile(ContactsManager manager) {
        try {
            File file = new File("contacts.txt");
            // Make sure file "contacts.txt" exits
            if (!file.exists()) {
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            // FilePointer just goes through the entier lenght of file
            while (raf.getFilePointer() < raf.length()) {
                // Call helper method for creation of Contact objects and 
                // storing them at the ContactsManager object.
                readContact(raf, manager);
            }
            // Close the file after reading.
            raf.close();
        } catch (NullPointerException e) {
            System.out.println("Pathname not given.");
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }

    /**
     * Method serving as helper to readFile. It takes one line at a time from
     * the file that is being read, splits it into String array at semicolons,
     * then sets the info in array elements as new Contact objects variables.
     * Lastly, it adds the created Contact object to the ContactsManager object.
     * 
     * @param raf RandomAccesFile object created at readFile, to access and read
     * "contacts.txt".
     * 
     * @param mngr ContactsManager object created and sent from ContactsApp 
     * class, where new Contact object will be stored after reading and 
     * creation.
     * 
     * @throws IOException if reading of the file goes wrong.
     * 
     * @throws IllegalArgumentException is thrown by the set-methods of 
     * Contact class, if info in "contacts.txt" is in wrong form.
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
     * Method to write Contact object data into contacts.txt. Similarly to 
     * readFile File and RandomAccessFile objects are created to access and 
     * write data into contacts.txt, but both objects are created for a 
     * temporary text file too, where the data is originally written. The 
     * contacts.txt file gets deleted and created again right afterwards, in
     * order to clear it. Then the info from temporary file is copied to 
     * contacts.txt.
     * 
     * @param manager ContactsManager object, that calls this method on itself
     * when contact database is altered.
     * 
     * @throws NullPointerException is thrown when pathname is not specified at
     * File object creation.
     * 
     * @throws IOException if anything goes wrong with file altering.
     */
    public static void writeFile(ContactsManager manager) {
        try {
            /*
             * Create a temp file, File object and RandomAccessFile object to
             * write the Contact objects there first, then to the contacts.txt.
             * The idea is to protect the info at original file, in case 
             * something goes wrong getting the contacts.
             */ 
            File tmpfile = new File("temp.txt");
            RandomAccessFile tempraf = new RandomAccessFile(tmpfile, "rws");

            /*
             * Get the ArrayList of contacts from ContactsManager object. Then
             * go through them, write them into String, then into the temp file.
             */
            ArrayList<Contact> contacts = manager.getContacts();
            for (Contact contact : contacts) {
                try {
                    tempraf.writeBytes(contact.ToStringWriteFile());
                    tempraf.writeBytes(System.lineSeparator());
                } catch (IOException e) {
                    System.out.println("Error writing into file");
                }
            }

            // Create File object
            File file = new File("contacts.txt");

            /*
             * Delete the file associated with the object. I haven't found a
             * way just to simply clear it.
             */

            file.delete();
            // Create the file again.
            if (!file.exists()) {
                file.createNewFile();
            }

            /*
             * Create RandomAccessFile object, to access and write into
             * contacts.txt.
             */
            RandomAccessFile raf = new RandomAccessFile(file, "rws");

            /*
             * Copy the contents of temp file to contacts.txt with a helper
             * method.
             */ 
            copyFile(tempraf, raf);

            /*
             * Close the RandomAccessFile objects and the files, and delete the
             * temp text file.
             */ 
            raf.close();
            tempraf.close();
            tmpfile.delete();
        } catch (NullPointerException e) {
            System.out.println("Pathname not given.");
        } catch (IOException e) {
            System.out.println("Error writing into file");
        }
    }

    /**
     * Method to help with file writing between the contacts.txt database file
     * and temp.txt temporary file, where contact info is first written into. 
     * This just copies the contents of temp.txt to contacts.txt via the 
     * RandomAccessFile objects associated with them.
     * 
     * @param original RandomAccessFile object of temp.txt/the file where info
     * is copied from
     * 
     * @param copy RandomAccessfile object of contacts.txt/the file where info
     * is copied to.
     * 
     * @throws IOException if anything goed wrong during the writing process.
     */
    public static void copyFile(
        RandomAccessFile original, RandomAccessFile copy) {
        try {
            // Set the FilePointer at both files to the start of the file
            original.seek(0);
            copy.seek(0);

            // Write line by line the original files contents into the copy.
            while (original.getFilePointer() < original.length()) {
                copy.writeBytes(original.readLine());
                copy.writeBytes(System.lineSeparator());
            }

        } catch (IOException e) {
            System.out.println("Error writing into file");
        }
        
    }
}
