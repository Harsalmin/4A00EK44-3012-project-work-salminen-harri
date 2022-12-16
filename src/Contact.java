/**
 * This is the class for contacts for the ContactsApp, of witch the contact 
 * objects are created from. Each Contact object has personal ID, first name, 
 * last name and phone number as mandatory information, as well as address and
 * e-mail as optional information. They are stored at the objects private
 * variables.
 * 
 * @author Harri Salminen
 */
public class Contact {
    private String personalId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String email;

    /**
     * Default constructor is empty since all variables are validated during 
     * the process of adding a new Contact object to the database.
     */
    public Contact() {
    }

    /**
     * Method for setting personal ID. Personal ID is formed from day, month and
     * year of birth, followed by +,- or A to indicate which century the
     * person was born at. That is followed by 3 digits ranging from
     * 002-899, and a checkmark at the end, being eiher a digit or capital
     * letter from group {A,B,C,D,E,F,H,J,K,L,M,N,P,R,S,T,U,V,W,X,Y. So in total
     * 11 characters, ddmmyyxnnnt. 
     * 
     * NOTE: the regular expression doesn't cover the actual dates and months 
     * yet.
     * 
     * @param personalId The personal ID that user inputs, which gets validated
     * against regular expression form.
     * 
     * @throws IllegalArgumentException if validation fails for any reason.
     */
    public void setPersonalId(String personalId) {
        if (personalId.matches(
            "[0-3]\\d[01]\\d{3}[+-A][0-8]\\d[2-9][0-9A-FHJ-NPR-Y]")) {
            this.personalId = personalId;
        } else {
            throw new IllegalArgumentException(
                "You did not enter the personal ID in correct format!");
        }
    }

    /**
     * Simple method to get the personal ID stored in the object.
     * 
     * @return The personal ID variable of object.
     */
    public String getPersonalId() {
        return personalId;
    }

    /**
     * Method for setting the first name of Contact object. First name has to
     * have a capital letter to start with, but it can contain uppercase and
     * lowercase letters, as well as dashes in it, like in Jari-Pekka.
     * 
     * @param firstName The first name that user inputs, which gets validated
     * against regular expression form.
     * 
     * @throws IllegalArgumentException if validation fails for any reason.
     */
    public void setFirstName(String firstName) {
        /*
         * \u00C5\u00C4\u00D6 are unicode codes for ÅÄÖ
         * \u00E5\u00E4\u00F6 are unicode codes for åäö
         */
        if (firstName.matches(
            "^[A-ZÅÄÖ\u00C5\u00C4\u00D6][a-zåäö\u00E5\u00E4\u00F6-]+[A-ZÅÄÖ " +
            "\u00C5\u00C4\u00D6a-zåäö\u00E5\u00E4\u00F6-]+[a-zåäö\u00E5\u00E4" +
            "\u00F6]$")) {
            this.firstName = firstName;
        } else {
            throw new IllegalArgumentException(
                "You did not enter the first name in correct format!\n");
        }
    }

    /**
     * Simple method to get the first name stored in the object
     * 
     * @return The first name variable of object
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Method for setting the last name of Contact object. Like first name, last
     * name has to have a capital letter to start with, but it can contain
     * uppercase and lowercase letters, as well as dashes in it, like in
     * Tuomainen-Suurela or McDonald.
     * 
     * @param lastName The last name that user inputs, which then gets validated
     * against a regular expression form
     * 
     * @throws IllegalArgumentException if the validation fails for any
     * reason.
     */
    public void setLastName(String lastName) {
        /*
         * \u00C5\u00C4\u00D6 are unicode codes for ÅÄÖ
         * \u00E5\u00E4\u00F6 are unicode codes for åäö
         */ 
        if (lastName.matches(
            "^[A-ZÅÄÖ\u00C5\u00C4\u00D6][a-zåäö\u00E5\u00E4\u00F6-]+[A-ZÅÄÖ " +
            "\u00C5\u00C4\u00D6a-zåäö\u00E5\u00E4\u00F6-]+[a-zåäö\u00E5\u00E4" +
            "\u00F6]$")) {
            this.lastName = lastName;
        } else {
            throw new IllegalArgumentException(
                "You did not enter the last name in correct format!\n");
        }
    }

    /**
     * Simple method to get the last name stored in the Contact object
     * 
     * @return The last name variable of object
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Method for setting the phone number of Contact object. Phone number must
     * start with Finnish country number +358, after which the phone number is
     * entered in one string of digits, skipping the first zero. Since the phone
     * number is at least 6 digits long without the leading zero, the regular
     * expression for validation reflects that. Phone numbers only contain
     * numbers in this case, after the country number.
     * 
     * @param phoneNumber The phone number user inputs for validation against
     * the regular expression.
     * 
     * @throws IllegalArgumentException if the validation fails for any reason.
     */
    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("^\\+358\\d{5}\\d+$")) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new IllegalArgumentException(
                "You did not enter the phone number in correct format!\n");
        }
    }

    /**
     * Simple method to get the phone number stored in the Contact object.
     * 
     * @return The phone number variable of object.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Method for setting the address for Contact object. The address starts
     * with capital letter, followed by string of letters of variable length,
     * followed by space and house number. The address can go on after this,
     * with apartment block and apartment number, for example. Address can be
     * left out suring object creation.
     * 
     * @param address The address user inputs, that gets validated against
     * the regular expression.
     * 
     * @throws IllegalArgumentException if validation fails for any reason.
     */
    public void setAddress(String address) {
        /*
         * \u00C5\u00C4\u00D6 are unicode codes for ÅÄÖ
         * \u00E5\u00E4\u00F6 are unicode codes for åäö
         */
        if (address.matches(
            "^[A-ZÅÄÖ\u00C5\u00C4\u00D6][a-zåäö\\såäö\u00E5\u00E4\u00F6-]+ " +
            "\\d{1,3}[A-Za-z0-9 ]*") || 
            address.equals("null") ||
            address.equals("")) {

            this.address = address;
        } else {
            throw new IllegalArgumentException(
                "You did not enter the address in correct format!\n");
        }
    }

    /**
     * Simple method to get the address stored at the object.
     * 
     * @return The address variable of object.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Method for setting the e-mail address for Contact object. The address
     * starts with username part, that can contain letters, digits, dashes and
     * dots, followed by @-symbol, followed by domain name, a dot and top-level
     * domain, such as .com or .fi.
     * 
     * NOTE: The regular expression is lacking,
     * missing support for top-level domains like .co.uk. Also the regular
     * expression is missing special characters like ?, % or !.
     * and å,ä and ö.
     * 
     * @param email The email user inputs for validation against the regular
     * expression.
     * 
     * @throws IllegalArgumentException if validation fails for any reason.
     */
    public void setEmail(String email) {
        if (email.matches(
            "^\\w+[\\w-.]*@\\w+((-\\w+)|(\\w*))\\.[a-z]{2,4}$") || 
            email.equals("null") ||
            email.equals("")) {

            this.email = email;
        } else {
            throw new IllegalArgumentException(
                "You did not enter the e-mail in correct format!\n");
        }
    }

    /**
     * Simple method to get the e-mail address in Contact object. 
     * 
     * @return The e-mail variable of object.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method is utilized in search and update menus for short listing of
     * available Contacts. 
     * 
     * @return Modified String containing personal ID, first name and last name
     * of the Contact object.
     */
    public String toStringShort() {
        return "ID: " + personalId +
        ", " + firstName + " " + lastName;
    }
    
    /**
     * Method for writing a contact in a file in a specific, csv-style text
     * line.
     * 
     * @return String with contact variables divided by semicolons.
     */
    public String ToStringWriteFile() {
        return  personalId + ";" +
                firstName + ";" +
                lastName + ";" +
                phoneNumber + ";" +
                address + ";" +
                email;
    }

    /**
     * Method to help print out the full dataset of the Contact object. Utilized
     * when updating a single entry or listing all object fully at main menu.
     * 
     * @return Modified String containing all the private variables of the 
     * Contact object.
     */
    @Override
    public String toString() {
        return "Personal ID: " + personalId +
        "\nFirst name: " + firstName +
        "\nLast name: " + lastName +
        "\nPhone number: " + phoneNumber +
        "\nAddress: " + address +
        "\nE-mail: " + email + "\n";
    }
}
