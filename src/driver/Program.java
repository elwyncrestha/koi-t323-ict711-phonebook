package driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import exception.UnknownPropertyException;
import handler.PhoneBookDataHandler;
import model.Contact;

/**
 * This is the driver class from where the program initiates.
 */
public class Program {

    private static PhoneBookDataHandler dataHandler;

    public static void main(String[] args) {
        showWelcomeMessage();

        boolean initPass = init();
        if (!initPass) {
            showFailedInitMessage();
            System.exit(0);
        }

    }

    private static void showWelcomeMessage() {
        String welcomeMsg = """
            | ------------------------------- |
            |            Phonebook            |
            |                                 |
            |        Welcome to the app!      |
            | ------------------------------- |
            """;
        System.out.println(welcomeMsg);
    }

    private static void showFailedInitMessage() {
        String welcomeMsg = """
            | ------------------------------- |
            |            Phonebook            |
            |                                 |
            |      Initialization failed!     |
            |      Check the data file.       |
            | ------------------------------- |
            """;
        System.out.println(welcomeMsg);
    }

    /**
     * An initialization method to import phone book data from the file.
     *
     * @return A flag to determine if the initialization processes succeeded or not.
     */
    private static boolean init() {
        dataHandler = new PhoneBookDataHandler();

        Optional<File> file = dataHandler.readFile();
        if (file.isEmpty()) {
            return false;
        }

        try {
            List<Contact> contactList = dataHandler.deserialize(file.get());
            dataHandler.setData(contactList);
        } catch (FileNotFoundException | DateTimeParseException | UnknownPropertyException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
}
