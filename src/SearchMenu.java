import java.io.Console;
import java.util.ArrayList;

/**
 * 
 */
public class SearchMenu {
    public static Console c = System.console();
    public static ContactsManager manager = ContactsApp.manager;

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
     * 
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
     * 
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
     * 
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
