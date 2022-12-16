import java.io.Console;

/**
 * 
 */
public class DeleteMenu {
    public static Console c = System.console();
    public static ContactsManager manager = ContactsApp.manager;

    /**
     * 
     */
    public static void deleteContactMenu() {
        System.out.println("Please select contact to delete: ");
        System.out.println(manager.displayAllContactsShort());
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
