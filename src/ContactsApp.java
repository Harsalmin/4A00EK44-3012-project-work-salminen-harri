import java.io.Console;

public class ContactsApp {
    public static Console c = System.console();
    public static ContactsManager manager = new ContactsManager();
    public static void main(String[] args) {
        try {
            FileHelper.readFile(manager);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
                AddMenu.addContactMenu();
                break;

            case 2:
                DeleteMenu.deleteContactMenu();
                break;

            case 3: 
                UpdateMenu.updateContactMenu();
                break;

            case 4:
                SearchMenu.searchMenu();
                break;
            
            case 5:
                try {
                    manager.displayAllContacts();
                } catch (IllegalArgumentException iae) {
                    System.out.println(iae.getMessage());
                }
                System.out.println();
                mainMenu();
                break;

            case 6:
                System.out.println(
                    "Thank you for using ContactsApp. Goodbye!\n");
                System.exit(0);
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