import java.io.Console;

/**
 * 
 */
public class AddMenu {
    public static Console c = System.console();
    public static ContactsManager manager = ContactsApp.manager;

    public static void addContactMenu() {
        System.out.println(
            "Please enter the following information for the new contanct.");
        System.out.println("Type \"cancel\" at any point to reverse the " +
            "creation process and get back to main menu");

        Contact newContact = new Contact();
        enterPersonalId(newContact);
    }

    /**
     * 
     * @param contact
     */
    public static void enterPersonalId(Contact contact) {
        System.out.println();
        System.out.print("Personal ID (mandatory)");
        System.out.println();
        System.out.println("""
            Please use the format of 6 digits followed by +/-/A followed
            by 3 digits and either a digit or letter from group
            {A,B,C,D,E,F,H,J,K,L,M,N,P,R,S,T,U,V,W,X,Y}. The first 6 digits
            are for day, month and year of birth.
            """);
        String answer = "";
        boolean inputSucces = false;
        do {
            try {
                System.out.println("Please enter the personal ID: ");
                answer = c.readLine().strip();
                if (answer.equalsIgnoreCase("cancel")) {
                    ContactsApp.mainMenu();
                }
                contact.setPersonalId(answer);
                System.out.println();
                inputSucces = true;
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }
        } while (!inputSucces && !answer.equals("cancel"));
        enterFirstName(contact);
    }

    /**
     * 
     * @param contact
     */
    public static void enterFirstName(Contact contact) {
        System.out.println();
        System.out.print("First name (mandatory)");
        System.out.println();
        System.out.println("""
            Please use the format of capital letter followed by letters or
            dash when entering new first name for contact.
            """);
        String answer = "";
        boolean inputSucces = false;
        do {
            try {
                System.out.println("Please enter the first name: ");
                answer = c.readLine().strip();
                if (answer.equalsIgnoreCase("cancel")) {
                    ContactsApp.mainMenu();
                }
                contact.setFirstName(answer);
                System.out.println();
                inputSucces = true;
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }
        } while (!inputSucces && !answer.equals("cancel"));
        enterLastName(contact);
    }

    /**
     * 
     * @param contact
     */
    public static void enterLastName(Contact contact) {
        System.out.println();
        System.out.print("Last name (mandatory)");
        System.out.println();
        System.out.println("""
            Please use the format of capital letter followed by letters or
            dash when entering new last name for contact. Last names are
            for example Suikkonen-Peippo, McDonald and Siekkinen, not 
            AErvinen or salminen.
            """);
        String answer = "";
        boolean inputSucces = false;
        do {
            try {
                System.out.println("Please enter the last name: ");
                answer = c.readLine().strip();
                if (answer.equalsIgnoreCase("cancel")) {
                    ContactsApp.mainMenu();
                }
                contact.setLastName(answer);
                System.out.println();
                inputSucces = true;
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }
        } while (!inputSucces && !answer.equals("cancel"));
        enterPhoneNumber(contact);
    }

    /**
     * 
     * @param contact
     */
    public static void enterPhoneNumber(Contact contact) {
        System.out.println();
        System.out.print("Phone number (mandatory)");
        System.out.println();
        System.out.println("""
            Please use the format of +358 plus the phone number without
            the first zero when entering new phone number.
            """);
        String answer = "";
        boolean inputSucces = false;
        do {
            try {
                System.out.println("Please enter the phone number: ");
                answer = c.readLine().strip();
                if (answer.equalsIgnoreCase("cancel")) {
                    ContactsApp.mainMenu();
                }
                contact.setPhoneNumber(answer);
                System.out.println();
                inputSucces = true;
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }
        } while (!inputSucces && !answer.equals("cancel"));
        enterAddress(contact);
    }

    /**
     * 
     * @param contact
     */
    public static void enterAddress(Contact contact) {
        System.out.println();
        System.out.print("Address (optional)");
        System.out.println();
        System.out.println("""
            Please use the format of capital letter followed by letters or
            dash or spaces followed by a space and digits when entering
            new address. You can leave the address blank.
            """);
        String answer = "answer";
        boolean inputSucces = false;
        do {
            try {
                System.out.println("Please enter the address: ");
                answer = c.readLine().strip();
                if (answer.equalsIgnoreCase("cancel")) {
                    ContactsApp.mainMenu();
                }
                contact.setAddress(answer);
                System.out.println();
                inputSucces = true;
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }
        } while (
            !inputSucces && !answer.equals("cancel") && !answer.equals(""));
        enterEmail(contact);
    }

    /**
     * 
     * @param contact
     */
    public static void enterEmail(Contact contact) {
        System.out.print("E-mail (optional)");
        System.out.println();
        System.out.println("""
            Please use the format of letters, dots, digits and other
            non-whitespace characters followed by @ followed by
            more non-whitespace characters, dot, more non-whitespace
            characters. You can leave the e-mail blank.
            """);
        String answer = "answer";
        boolean inputSucces = false;
        do {
            try {
                System.out.println("Please enter the email: ");
                answer = c.readLine().strip();
                if (answer.equalsIgnoreCase("cancel")) {
                    ContactsApp.mainMenu();
                }
                contact.setEmail(answer);
                System.out.println();
                inputSucces = true;
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }
        } while (
            !inputSucces && !answer.equals("cancel") && !answer.equals(""));
        try {
            System.out.println(manager.addContact(contact));
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }
        System.out.println();
        ContactsApp.mainMenu();
    }
}
