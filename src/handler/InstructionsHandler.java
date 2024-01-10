package handler;

import static constant.AppConstant.ADD;
import static constant.AppConstant.DELETE;
import static constant.AppConstant.INSTRUCTION_FILE_NAME;
import static constant.AppConstant.QUERY;
import static constant.AppConstant.SAVE;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Stream;

import exception.UnknownPropertyException;
import model.Contact;
import model.Instruction;
import model.InstructionType;
import specs.FileHandlerImpl;
import utility.DateUtility;

/**
 * An implementation class that handles phone book instructions.
 * Note: This class is only used for handling the instructions, not the data.
 * Check {@link PhoneBookDataHandler} for the data.
 */
public class InstructionsHandler extends FileHandlerImpl<Instruction> {

    public InstructionsHandler() {
        super(INSTRUCTION_FILE_NAME);
    }

    @Override
    public Instruction getInstance() {
        return new Instruction();
    }

    @Override
    public void mapContentToModel(Instruction model, String content)
        throws DateTimeParseException, UnknownPropertyException {
        String[] keyValue = content.split(" ", 2);
        String key = keyValue[0];
        String value = key.equals(SAVE) ? null : keyValue[1].trim();

        Contact contact = new Contact();

        switch (key) {
            case ADD -> {
                model.setType(InstructionType.ADD);
                String[] addInputs = value.split(";");
                for (String c : addInputs) {
                    PhoneBookDataHandler.mapContentToContact(contact, c.trim());
                }
            }
            case DELETE -> {
                model.setType(InstructionType.DELETE);
                String[] deleteInputs = value.split(";");
                contact.setName(deleteInputs[0].trim());
                contact.setBirthday(DateUtility.parse(deleteInputs[1].trim()));
            }
            case SAVE -> model.setType(InstructionType.SAVE);
            case QUERY -> {
                model.setType(InstructionType.QUERY);
                String[] queryInputs = value.split(";");
                for (String c : queryInputs) {
                    PhoneBookDataHandler.mapContentToContact(contact, c.trim());
                }
            }
            default -> throw new UnknownPropertyException(key);
        }

        model.setContact(contact);
    }

    public List<Contact> run(List<Instruction> instructionList, List<Contact> contactList) {
        for (Instruction instruction : instructionList) {
            Contact contact = instruction.getContact();
            switch (instruction.getType()) {
                case ADD -> contactList.add(contact);
                case DELETE -> contactList.removeIf(
                    c -> c.getName().equals(contact.getName()) && c.getBirthday()
                        .isEqual(contact.getBirthday()));
                case QUERY -> {
                    Stream<Contact> stream = contactList.stream();
                    if (contact.getName() != null && !contact.getName().isEmpty()) {
                        stream = stream.filter(
                            c -> c.getName() != null && c.getName().equals(contact.getName()));
                    }
                    if (contact.getBirthday() != null) {
                        stream = stream.filter(c -> c.getBirthday() != null && c.getBirthday()
                            .isEqual(contact.getBirthday()));
                    }
                    if (contact.getPhone() != null && !contact.getPhone().isEmpty()) {
                        stream = stream.filter(
                            c -> c.getPhone() != null && c.getPhone().equals(contact.getPhone()));
                    }
                    if (contact.getEmail() != null && !contact.getEmail().isEmpty()) {
                        stream = stream.filter(
                            c -> c.getEmail() != null && c.getEmail().equals(contact.getEmail()));
                    }
                    if (contact.getAddress() != null && !contact.getAddress().isEmpty()) {
                        stream = stream.filter(c -> c.getAddress() != null && c.getAddress()
                            .equals(contact.getAddress()));
                    }
                    stream.map(Contact::toString).forEach(System.out::println);
                }
                case SAVE -> {

                }
            }
        }
        return contactList;
    }
}
