import java.io.Console;

/**
 * The main class of ContactsApp, that is called to run the app. 
 */
public class ContactsApp {
    public static Console c = System.console();
    public static ContactsManager manager = new ContactsManager();

    /**
     * After running java on ContactsApp, this method is called and a new 
     * ContactsManager object is created before showing the menu, where all the 
     * contacts will be stored to do operations on them. After creation of the 
     * object, it is sent to FileHelpers readFile to populate the ArrayList of 
     * the object with contact info already in the contacts.txt database. 
     * After that the menu shows and user can start doing CRUD operations on 
     * the contacts.
     * 
     * @param args Arguments given on running the app. Not used on 
     * ContactsApp.
     */
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

    /**
     * Main menu method for ContactsApp. Here the user is displayed the 
     * different options on what they can do with the contacts.
     */
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

    /**
     * Helper method for mainMenu. User is asked to choose an option to 
     * perform. With the help of askOption and askNumber, user can only input 
     * valid integers. Then a switch case is used to call the appropriate 
     * methods.
     */
    public static void mainMenuSwitch() {
        /* 
         * Ask user for the option in the main menu to perform. Since there are 
         * 6 options, user input should be between 1 and 6.
         */
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
                    System.out.println(manager.displayAllContacts());
                } catch (IllegalArgumentException iae) {
                    System.out.println(iae.getMessage());
                }
                mainMenu();
                break;

            case 6:
                System.out.println(
                    "Thank you for using ContactsApp. Goodbye!\n");
                System.exit(0);
        }
    }

    /**
     * Method used to ask what action the user wants to perform in the menus. It
     * takes the maximum and minimum integer parameters the user should be able 
     * to choose, and utilizes askNumber for validation. Returns the valid 
     * option as integer number.
     * 
     * @param max Maximum allowed integer for user to choose from.
     * 
     * @param min Minimum allowed integer for user to choose from.
     * 
     * @return The option as integer the user wants to perform.
     */
    public static int askOption(int max, int min) {
        // Just initialize option as something out of range
        int option = min - 1;
        do {
            System.out.print("Enter your selection (number): ");
            try {
                option = askNumber(max, min);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (option == min - 1);
        return option;
    }

    /**
     * Method to ask a valid number in the range {@code [min, max]} from user.
     * It validates the input, and if the number doesn't fit the range or is
     * not number at all, an exception is thrown, which is handled at askOption.
     * 
     * @param max Maximum allowed integer
     * 
     * @param min Minimum allowed integer
     * 
     * @throws IllegalArgumentException if integer isn't on the range 
     * {@code [min, max]} or the input parsing to integer fails.
     * 
     * @return Valid integer from range {@code [min, max]}
     */
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