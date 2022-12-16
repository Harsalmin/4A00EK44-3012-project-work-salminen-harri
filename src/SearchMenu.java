import java.io.Console;
import java.util.ArrayList;

/**
 * Menu class for searhing for contacts in database. The user has option to 
 * search contact by index and by keyword.
 */
public class SearchMenu {
    public static Console c = System.console();
    public static ContactsManager manager = ContactsApp.manager;

    /**
     * The menu method for displaying searching options.
     */
    public static void searchMenu() {
        System.out.println("How would you like to perform a contact search?");
        System.out.println();
        System.out.println("1. Through index");
        System.out.println("2. Through keyword");
        System.out.println("3. Back to main menu");
        System.out.println();
        searchMenuSwitch();
    }

    /**
     * The method that actually asks the user to choose an option displayed at 
     * searchMenu(), then performs the action according to the selection.
     */
    public static void searchMenuSwitch() {
        int option = ContactsApp.askOption(3, 1);
        switch (option) {
            case 1:
                searchContactIndex();
                break;
            case 2:
                searchContactKeyword();
                break;
            case 3:
                ContactsApp.mainMenu();
        }
    }

    /**
     * The method for performing a contact search by index. All contacts are 
     * listed, with short info containing their personal ID, first name and 
     * last name. They are indexed 1. -> as options to choose from. There is 
     * also "go back" option. The option number is used to determine the index 
     * of the contact to be searched from the ContactsManager ArrayList.
     */
    public static void searchContactIndex() {
        System.out.println("Please select contact to view:");
        System.out.println();
        System.out.println(manager.displayAllContactsShort());
        System.out.println(
                manager.getContacts().size() + 1 + ". Back to search menu");
        System.out.println();
        int option = ContactsApp.askOption(manager.getContacts().size() + 1, 1);
        if (option == manager.getContacts().size() + 1) {
            searchMenu();
        } else {
            System.out.println(
                manager.getContact(option - 1).toString());
            searchMenu();
        }
    }

    /**
     * The method to search for contact by specific keyword. The method 
     * utilizes the ContactsManagers {@code searchKeyword(String keyword)} 
     * method to look for the keyword in Contact objects by putting all the 
     * contact info into one String and looking for a match. If match is found, 
     * the Contact object is added in a result ArrayList. The ArrayList is 
     * returned from there and it's contents will be displayed here, if there 
     * were any results.
     */
    public static void searchContactKeyword() {
        System.out.print("Please enter a keyword to search for: ");
        String keyword = c.readLine();
        ArrayList<Contact> results = manager.searchKeyword(keyword);
        if (results.isEmpty()) {
            System.out.println("No contacts found with that keyword");
        } else {
            System.out.println("Found following results:");
            System.out.println();
            for (Contact contact : results) {
                System.out.println(contact.toString());
            }
        }
        searchMenu();
    }
}
