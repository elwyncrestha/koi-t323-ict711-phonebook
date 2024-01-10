package handler;

import static constant.AppConstant.ADDRESS;
import static constant.AppConstant.BIRTHDAY;
import static constant.AppConstant.DATA_FILE_NAME;
import static constant.AppConstant.EMAIL;
import static constant.AppConstant.NAME;
import static constant.AppConstant.PHONE;

import java.time.format.DateTimeParseException;

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
}
