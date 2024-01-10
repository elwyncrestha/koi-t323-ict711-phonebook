package handler;

import static constant.AppConstant.ADDRESS;
import static constant.AppConstant.BIRTHDAY;
import static constant.AppConstant.DATA_FILE_NAME;
import static constant.AppConstant.EMAIL;
import static constant.AppConstant.NAME;
import static constant.AppConstant.PHONE;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exception.UnknownPropertyException;
import model.Contact;
import specs.FileHandlerImpl;
import utility.DateUtility;

/**
 * An implementation class that handles phone book data.
 * Note: This class is only used for handling the data, not the instructions.
 * Check {@link InstructionsHandler} for the data.
 */
public class PhoneBookDataHandler extends FileHandlerImpl<List<Contact>> {

    public PhoneBookDataHandler() {
        super(DATA_FILE_NAME);
    }

    private static void mapContentToContact(Contact contact, String content)
        throws DateTimeParseException, UnknownPropertyException {
        String[] keyValue = content.split(" ", 2);
        String key = keyValue[0];
        String value = keyValue[1].trim();

        switch (key) {
            case NAME -> contact.setName(value);
            case BIRTHDAY -> contact.setBirthday(DateUtility.parse(value));
            case PHONE -> contact.setPhone(value);
            case EMAIL -> contact.setEmail(value);
            case ADDRESS -> contact.setAddress(value);
            default -> throw new UnknownPropertyException(key);
        }
    }

    @Override
    public List<Contact> deserialize(File file)
        throws FileNotFoundException, DateTimeParseException, UnknownPropertyException {
        List<Contact> contactList = new ArrayList<>();

        try (final Scanner scanner = new Scanner(file)) {

            Contact contact = null;
            boolean hasNextLine = scanner.hasNextLine();
            while (hasNextLine) {
                String line = scanner.nextLine();

                // C1: If contact object is not null and line is empty, it denotes an end of the entry details.
                if (contact != null && line.trim().isEmpty()) {
                    contactList.add(contact);
                    contact = null;
                    continue;
                }

                if (contact == null) {
                    contact = new Contact();
                }
                mapContentToContact(contact, line);

                hasNextLine = scanner.hasNextLine();
                // Similar to above condition C1, if the current line is the last line, it denotes an end of the entry details.
                if (!hasNextLine) {
                    contactList.add(contact);
                }
            }
        }
        return contactList;
    }
}
