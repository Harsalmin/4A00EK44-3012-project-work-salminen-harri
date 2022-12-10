public class ContactsApp {
    public static void main(String[] args) {
        ContactsManager manager = new ContactsManager();
        FileHelper.readFile(manager);
        
        manager.updateAddress(2, "Insinöörinkatu 1");
    }

    public static void mainMenu() {

    }
}