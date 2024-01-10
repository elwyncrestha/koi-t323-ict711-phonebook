package handler;

import static constant.AppConstant.ADDRESS;
import static constant.AppConstant.BIRTHDAY;
import static constant.AppConstant.DATA_FILE_NAME;
import static constant.AppConstant.EMAIL;
import static constant.AppConstant.NAME;
import static constant.AppConstant.PHONE;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import exception.MethodNotImplementedException;
import exception.UnknownPropertyException;
import model.Contact;
import specs.FileHandlerImpl;
import utility.DateUtility;

/**
 * An implementation class that handles phone book data.
 * Note: This class is only used for handling the data, not the instructions.
 * Check {@link InstructionsHandler} for the data.
 */
public class PhoneBookDataHandler extends FileHandlerImpl<Contact> {

    public PhoneBookDataHandler() {
        super(DATA_FILE_NAME);
    }

    public static void mapContentToContact(Contact contact, String content)
        throws DateTimeParseException, UnknownPropertyException {
        String[] keyValue = content.split(" ", 2);
        String key = keyValue[0].trim();
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
    public Contact getInstance() {
        return new Contact();
    }

    @Override
    public void mapContentToModel(Contact model, String content)
        throws DateTimeParseException, UnknownPropertyException {
        mapContentToContact(model, content);
    }

    @Override
    public void serialize() throws MethodNotImplementedException, FileNotFoundException {
        List<String> contents = new ArrayList<>();

        data.forEach(contact -> {
            String name = contact.getName();
            if (name != null && !name.isEmpty()) {
                contents.add(String.format("%s %s", NAME, name));
            }

            LocalDate birthday = contact.getBirthday();
            if (birthday != null) {
                contents.add(String.format("%s %s", BIRTHDAY, DateUtility.toString(birthday)));
            }

            String phone = contact.getPhone();
            if (phone != null && !phone.isEmpty()) {
                contents.add(String.format("%s %s", PHONE, phone));
            }

            String email = contact.getEmail();
            if (email != null && !email.isEmpty()) {
                contents.add(String.format("%s %s", EMAIL, email));
            }

            String address = contact.getAddress();
            if (address != null && !address.isEmpty()) {
                contents.add(String.format("%s %s", ADDRESS, address));
            }

            contents.add("");
        });

        writeFile(contents);
    }
}
