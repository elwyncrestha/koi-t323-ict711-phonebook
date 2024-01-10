package handler;

import static constant.AppConstant.ADD;
import static constant.AppConstant.DELETE;
import static constant.AppConstant.INSTRUCTION_FILE_NAME;
import static constant.AppConstant.QUERY;
import static constant.AppConstant.SAVE;

import java.time.format.DateTimeParseException;
import java.util.List;

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

    public boolean run(List<Instruction> instructionList) {
        // TODO: Run the instruction.
        return false;
    }
}
