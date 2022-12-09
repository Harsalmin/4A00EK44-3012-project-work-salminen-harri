public class ContactsApp {
    public static void main(String[] args) {
        ContactsManager manager = new ContactsManager();
        FileHelper.readFile(manager);
        manager.displayAllContacts();
    }
}