import java.io.Console;

/**
 * Menu class for deleting/removing contacts. User gets a list of shortened
 * contact info and is asked to choose contact option to delete. The option is 
 * used to determine the index of the contact in the contacts ArrayList.
 * 
 * @author Harri Salminen
 */
public class DeleteMenu {
    /**
     * Console for user inputs
     */
    public static Console c = System.console();

    /**
     * DeleteMenu uses the same ContactsManager object for Contact object adding
     * as ContactsApp.
     */
    public static ContactsManager manager = ContactsApp.manager;

    /**
     * The main method for contact deletion. First user is displayed the 
     * contact information in short version, and prompted to choose the contact 
     * to delete or go back to main menu. The user is asked for confirmation for
     * deleting the contact. The option user chooses is used as 
     * index of the contact to be removed.
     */
    public static void deleteContactMenu() {
        System.out.println("Please select contact to delete: ");
        System.out.println(manager.displayAllContactsShort());
        /*
         * Reason for using getContacts().size() + 1 is because the contacts 
         * are listed with their index + 1
         */
        System.out.println(
            manager.getContacts().size() + 1 + ". Back to main menu");
        System.out.println();
        int option = ContactsApp.askOption(manager.getContacts().size() + 1, 1);
        if (option == manager.getContacts().size() + 1) {
            ContactsApp.mainMenu();
        } else {
            System.out.print(
                "Are you sure you want to delete this contact? (y/n): ");
            char answer = c.readLine().charAt(0);
            if (answer == 'y') {
                try {
                    System.out.println(
                    manager.deleteContact(manager.getContact(option - 1)));
                } catch (NullPointerException npe) {
                    System.out.println(npe.getMessage());
                } catch (IllegalArgumentException iae) {
                    System.out.println(iae.getMessage());
                }
            }
        }
        System.out.println();
        ContactsApp.mainMenu();
    }
}
