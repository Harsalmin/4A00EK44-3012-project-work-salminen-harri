import java.io.Console;

public class DeleteMenu {
    public static Console c = System.console();
    public static ContactsManager manager = ContactsApp.manager;

    public static void deleteContactMenu() {
        System.out.println("Please select contact to delete: ");
        manager.displayAllContactsShort();
        System.out.println(
                manager.getContacts().size() + 1 + ". Back to main menu");
        System.out.println();
        int option = ContactsApp.askOption(manager.getContacts().size() + 1, 1);
        if (option == manager.getContacts().size() + 1) {
            ContactsApp.mainMenu();
        } else {
            manager.deleteContact(manager.getContact(option - 1));
        }
        ContactsApp.mainMenu();
    }
}
