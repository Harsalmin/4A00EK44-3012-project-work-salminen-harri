import java.io.Console;

/**
 * The menu class for updating existing contacts. The update are itemized for 
 * each variable, and validated before update goes through.
 * 
 * @author Harri Salminen
 */
public class UpdateMenu {
    /**
     * Console for user inputs
     */
    public static Console c = System.console();

    /**
     * UpdateMenu uses the same ContactsManager object for Contact object adding
     * as ContactsApp.
     */
    public static ContactsManager manager = ContactsApp.manager;

    /**
     * The main update menu method. Displays all contact information as short 
     * versions, only displaying personal ID, first name and last name. Then 
     * user is asked to pick an option, or go back to main menu. The contact 
     * index gets taken from the option chosen, and sent forward.
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
     * After picking a contact to update in the updateContactMenu(), this menu 
     * is shown, asking the user to specify which information about the 
     * contact they want to update. The method takes the index of contact in 
     * question from the previous menu, and displays the contact here in full.
     * 
     * @param index Index of the Contact object to be updated in the 
     * ContactsManager object ArrayList.
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
     * The menu switch method after updateContactItemMenu, which asks for the 
     * option of what user wants to update or go back to main update menu. 
     * After choosing the option, an appropriate method is called.
     *  
     * @param index Index of the Contact object to be updated in the 
     * ContactsManager object ArrayList.
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
     * The method for updating personal ID. The user gets some instructions on
     * what the personal ID should look like. Then the user is prompted to
     * enter the personal ID update of their choosing. If the update fails,
     * an exception is thrown all the way from Contact class validations to
     * here, and the message is displayed. Success message is also shown, along
     * with the updated contact information. Either way, the user is sent back
     * to the update item selection menu.
     * 
     * @param index Index of the Contact object to be updated in the
     *              ContactsManager object ArrayList.
     */
    public static void updateId(int index) {
        System.out.println("""
                Please use the format of 6 digits followed by +/-/A followed
                by 3 digits and either a digit or letter from group
                {A,B,C,D,E,F,H,J,K,L,M,N,P,R,S,T,U,V,W,X,Y}.The first 6 digits
                are for day, month and year of birth, for example 300499-675T.
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
     * The method for updating first name. The user gets some instructions on
     * what the first name should look like. Then the user is prompted to
     * enter the first name update of their choosing. If the update fails,
     * an exception is thrown all the way from Contact class validations to
     * here, and the message is displayed. Success message is also shown, along
     * with the updated contact information. Either way, the user is sent back
     * to the update item selection menu.
     * 
     * @param index Index of the Contact object to be updated in the
     *              ContactsManager object ArrayList.
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
     * The method for updating last name. The user gets some instructions on
     * what the last nane should look like. Then the user is prompted to
     * enter the last name update of their choosing. If the update fails,
     * an exception is thrown all the way from Contact class validations to
     * here, and the message is displayed. Success message is also shown, along
     * with the updated contact information. Either way, the user is sent back
     * to the update item selection menu.
     * 
     * @param index Index of the Contact object to be updated in the
     *              ContactsManager object ArrayList.
     */
    public static void updateLastName(int index) {
        System.out.println("""
            Please use the format of capital letter followed by letters or
            dash when entering new last name for contact. Last names are
            for example Suikkonen-Peippo, McDonald and Siekkinen, not
            AErvinen or salminen.
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
     * The method for updating phone number. The user gets some instructions on
     * what the phone number should look like. Then the user is prompted to
     * enter the phone number update of their choosing. If the update fails,
     * an exception is thrown all the way from Contact class validations to
     * here, and the message is displayed. Success message is also shown, along 
     * with the updated contact information. Either way, the user is sent back 
     * to the update item selection menu.
     * 
     * @param index Index of the Contact object to be updated in the
     *              ContactsManager object ArrayList.
     */
    public static void updatePhoneNumber(int index) {
        System.out.println("""
                Please use the format of +358 plus the phone number without
                the first zero when entering new phone number. Phone number 
                should be at 6 digits long, for example +358406767.
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
     * The method for updating address. The user gets some instructions on
     * what the address should look like. Then the user is prompted to
     * enter the address update of their choosing. If the update fails,
     * an exception is thrown all the way from Contact class validations to
     * here, and the message is displayed. Success message is also shown, along
     * with the updated contact information. Either way, the user is sent back
     * to the update item selection menu.
     * 
     * @param index Index of the Contact object to be updated in the
     *              ContactsManager object ArrayList.
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
     * The method for updating e-mail. The user gets some instructions on
     * what the e-mail should look like. Then the user is prompted to
     * enter the e-mail update of their choosing. If the update fails,
     * an exception is thrown all the way from Contact class validations to
     * here, and the message is displayed. Success message is also shown, along
     * with the updated contact information. Either way, the user is sent back
     * to the update item selection menu.
     * 
     * @param index Index of the Contact object to be updated in the
     *              ContactsManager object ArrayList.
     */
    public static void updateEmail(int index) {
        System.out.println("""
                Please use the format of letters, dots, digits and other
                non-whitespace characters followed by @ followed by
                letters, digits, dots or dashes, followed by a dot and 2 to 4
                letters signifying the top-level domain. You can leave the 
                e-mail blank.
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
