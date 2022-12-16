import java.io.Console;

/**
 * 
 */
public class UpdateMenu {
    public static Console c = System.console();
    public static ContactsManager manager = ContactsApp.manager;

    /**
     * 
     */
    public static void updateContactMenu() {
        System.out.println("Please select contact to update: ");
        System.out.println(manager.displayAllContactsShort());
        System.out.println(
                manager.getContacts().size() + 1 + ". Back to main menu");
        System.out.println();
        int option = ContactsApp.askOption(manager.getContacts().size() + 1, 1);
        if (option == manager.getContacts().size() + 1) {
            ContactsApp.mainMenu();
        } else {
            updateContactItemMenu(option - 1);
        }
    }

    /**
     * 
     * @param index
     */
    public static void updateContactItemMenu(int index) {
        Contact contact = manager.getContact(index);
        System.out.println(contact.toString());
        System.out.println();
        System.out.println("What would you like to update?");
        System.out.println();
        System.out.println("1. Personal ID");
        System.out.println("2. First name");
        System.out.println("3. Last name");
        System.out.println("4. Phone number");
        System.out.println("5. Address");
        System.out.println("6. E-mail");
        System.out.println("7. Back to update menu");
        System.out.println();
        updateMenuSwitch(index);
    }

    /**
     * 
     * @param index
     */
    public static void updateMenuSwitch(int index) {
        int option = ContactsApp.askOption(7, 1);
        switch (option) {
            case 1:
                updateId(index);
                break;
            case 2:
                updateFirstName(index);
                break;
            case 3:
                updateLastName(index);
                break;
            case 4:
                updatePhoneNumber(index);
                break;
            case 5:
                updateAddress(index);
                break;
            case 6:
                updateEmail(index);
                break;
            case 7:
                updateContactMenu();
        }
    }

    /**
     * 
     * @param index
     */
    public static void updateId(int index) {
        System.out.println("""
                Please use the format of 6 digits followed by +/-/A followed
                by 3 digits and either a digit or letter from group
                {A,B,C,D,E,F,H,J,K,L,M,N,P,R,S,T,U,V,W,X,Y}.The first 6 digits
                are for day, month and year of birth.
                """);
        System.out.print("Please enter new personal ID: ");
        try {
            String newId = c.readLine().strip();
            System.out.println();
            System.out.println(manager.updatePersonalId(index, newId));
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }
        System.out.println();
        updateContactItemMenu(index);
    }

    /**
     * 
     * @param index
     */
    public static void updateFirstName(int index) {
        System.out.println("""
                Please use the format of capital letter followed by letters or
                dash when entering new first name for contact.
                """);
        System.out.println("Please enter new first name: ");
        try {
            String newFirstName = c.readLine().strip();
            System.out.println();
            System.out.println(manager.updateFirstName(index, newFirstName));
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }
        System.out.println();
        updateContactItemMenu(index);
    }

    /**
     * 
     * @param index
     */
    public static void updateLastName(int index) {
        System.out.println("""
                Please use the format of capital letter followed by letters or
                dash when entering new last name for contact.
                """);
        System.out.println("Please enter new last name: ");
        try {
            String newLastName = c.readLine().strip();
            System.out.println();
            System.out.println(manager.updateLastName(index, newLastName));
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }
        System.out.println();
        updateContactItemMenu(index);
    }

    /**
     * 
     * @param index
     */
    public static void updatePhoneNumber(int index) {
        System.out.println("""
                Please use the format of +358 plus the phone number without
                the first zero when entering new phone number.
                """);
        System.out.println("Please enter new phone number: ");
        try {
            String newPhoneNumber = c.readLine().strip();
            System.out.println();
            System.out.println(
                    manager.updatePhoneNumber(index, newPhoneNumber));
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }
        System.out.println();
        updateContactItemMenu(index);
    }

    /**
     * 
     * @param index
     */
    public static void updateAddress(int index) {
        System.out.println("""
                Please use the format of capital letter followed by letters or
                dash or spaces followed by a space and digits when entering
                new address. You can leave the address blank.
                """);
        System.out.println("Please enter new address: ");
        try {
            String newAddress = c.readLine().strip();
            System.out.println();
            System.out.println(manager.updateAddress(index, newAddress));
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }
        System.out.println();
        updateContactItemMenu(index);
    }

    /**
     * 
     * @param index
     */
    public static void updateEmail(int index) {
        System.out.println("""
                Please use the format of letters, dots, digits and other
                non-whitespace characters followed by @ followed by
                letters, digits, dots or dashes, followed by a dot and 2 to 4
                letters signifying the top-level domain
                """);
        System.out.println("Please enter new email: ");
        try {
            String newEmail = c.readLine().strip();
            System.out.println();
            System.out.println(manager.updateEmail(index, newEmail));
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }
        System.out.println();
        updateContactItemMenu(index);
    }
}
