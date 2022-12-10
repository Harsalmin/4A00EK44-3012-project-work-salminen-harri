import java.io.Console;

public class ContactsApp {
    public static Console c = System.console();
    public static ContactsManager manager = new ContactsManager();
    public static void main(String[] args) {
        FileHelper.readFile(manager);
        System.out.println("Welcome to ContactsApp!");
        System.out.println();
        mainMenu();
    }

    public static void mainMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1. Add a contact");
        System.out.println("2. Delete a contact");
        System.out.println("3. Update a contact");
        System.out.println("4. Search a contact");
        System.out.println("5. Display all contacts");
        System.out.println("6. Exit program");
        System.out.println();
        mainMenuSwitch();
    }

    public static void mainMenuSwitch() {
        int option = askOption(6, 1);
        System.out.println();
        
        switch(option) {
            case 1:
                addContactMenu();
                break;

            case 2:
                deleteContactMenu();
                mainMenu();
                break;

            case 3: 
                updateContactMenu();
                break;

            case 4:
            break;
            
            case 5:
                manager.displayAllContacts();
                mainMenu();
                break;

            case 6:
            System.out.println("Thank you for using ContactsApp. Goodbye!\n");
            System.exit(0);
        }
    }

    public static void addContactMenu() {
        System.out.println(
            "Please enter the following information for the new contanct.");
        System.out.println("Type \"cancel\" at any point to reverse the " + 
        "creation process and get back to main menu");
        String answer = "";
        while (!answer.equalsIgnoreCase("cancel")) {
            System.out.print("Personal ID (mandatory): ");
            answer = c.readLine();
            System.out.print("First name (mandatory): ");
            System.out.print("Last name (mandatory): ");
            System.out.print("Phone number (mandatory): ");
            System.out.print("Address (optional): ");
            System.out.print("E-mail (optional): ");
        }
        mainMenu();
    }

    public static void updateContactMenu() {
        System.out.println("Please select contact to update: ");
        manager.displayAllContactsShort();
        System.out.println(
            manager.getContacts().size() + 1 + ". Back to main menu");
        System.out.println();
        int option = askOption(manager.getContacts().size() + 1, 1);
        if (option == manager.getContacts().size() + 1) {
            mainMenu();
        } else {
            updateContactItemMenu(option - 1);
        }
    }

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
        System.out.println("7. Back to main menu");
        System.out.println();
        updateMenuSwitch();
    }

    public static void updateMenuSwitch() {
        int option = askOption(7, 1);
        switch(option) {
            case 1:
            break;
            case 2:
            break;
            case 3:
            break;
            case 4:
            break;
            case 5:
            break;
            case 6:
            break;
            case 7:
                mainMenu();
        }
    }

    public static void deleteContactMenu() {
        System.out.println("Please select contact to delete: ");
        manager.displayAllContactsShort();
        System.out.println(
                manager.getContacts().size() + 1 + ". Back to main menu");
        System.out.println();
        int option = askOption(manager.getContacts().size() + 1, 1);
        if (option == manager.getContacts().size() + 1) {
            mainMenu();
        } else {
            manager.deleteContact(manager.getContact(option - 1));
        }
    }

    public static int askOption(int max, int min) {
        int option = 0;
        do {
            System.out.print("Enter your selection (number): ");
            try {
                option = askNumber(max, min);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (option == 0);
        return option;
    }

    public static int askNumber(int max, int min) {
        int number = 0;
        try {
            number = Integer.parseInt(c.readLine());
            if (number > max || number < min) {
                throw new IllegalArgumentException(
                    "Please enter a valid number option");
            } else {
                return number;
            }
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Please enter a number");
        }
    }
}