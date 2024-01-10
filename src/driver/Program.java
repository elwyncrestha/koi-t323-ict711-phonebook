package driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import exception.MethodNotImplementedException;
import exception.UnknownPropertyException;
import handler.InstructionsHandler;
import handler.PhoneBookDataHandler;
import model.Contact;
import model.Instruction;
import specs.Runner;
import utility.LogUtility;

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

        showTriggerInstructionPrompt();
    }

    private static void showWelcomeMessage() {
        String msg = """
            | ------------------------------- |
            |            Phonebook            |
            |                                 |
            |        Welcome to the app!      |
            | ------------------------------- |
            """;
        System.out.println(msg);
    }

    private static void showFailedInitMessage() {
        String msg = """
            | ------------------------------- |
            |            Phonebook            |
            |                                 |
            |      Initialization failed!     |
            |      Check the data file.       |
            | ------------------------------- |
            """;
        System.out.println(msg);
    }

    private static void showTriggerInstructionPrompt() {
        String msg = """
            | ------------------------------- |
            |            Phonebook            |
            |                                 |
            |    Would you like to trigger    |
            |    the instructions? (y/n)      |
            | ------------------------------- |
            """;
        System.out.println(msg);

        final Scanner scanner = new Scanner(System.in);
        char ch = scanner.nextLine().toCharArray()[0];

        if (ch == 'y') {
            boolean runPass = runInstructions();
            if (!runPass) {
                showFailedRunMessage();
            }
        }
    }

    private static void showFailedRunMessage() {
        String msg = """
            | ------------------------------- |
            |            Phonebook            |
            |                                 |
            |     Run instructions failed!    |
            |     Check the file.             |
            | ------------------------------- |
            """;
        System.out.println(msg);
    }

    /**
     * An initialization method to import phone book data from the file.
     *
     * @return A flag to determine if the initialization process succeeded or not.
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
            LogUtility.log(e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * A method to read and run the instructions from the file.
     *
     * @return A flag to determine if the run process succeeded or not.
     */
    private static boolean runInstructions() {
        InstructionsHandler instructionsHandler = new InstructionsHandler();

        Optional<File> file = instructionsHandler.readFile();
        if (file.isEmpty()) {
            return false;
        }

        try {
            List<Instruction> list = instructionsHandler.deserialize(file.get());
            Runner saveCallback = () -> {
                try {
                    dataHandler.serialize();
                } catch (MethodNotImplementedException | FileNotFoundException e) {
                    LogUtility.log(e.getMessage());
                }
            };
            dataHandler.setData(instructionsHandler.run(list, dataHandler.getData(), saveCallback));
        } catch (FileNotFoundException | DateTimeParseException | UnknownPropertyException e) {
            LogUtility.log(e.getMessage());
            return false;
        }

        return true;
    }
}
