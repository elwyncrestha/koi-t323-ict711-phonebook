package handler;

import static constant.AppConstant.ADD;
import static constant.AppConstant.DELETE;
import static constant.AppConstant.INSTRUCTION_FILE_NAME;
import static constant.AppConstant.QUERY;
import static constant.AppConstant.SAVE;

import java.time.format.DateTimeParseException;
import java.util.List;

import exception.UnknownPropertyException;
import model.Instruction;
import specs.FileHandlerImpl;

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

        switch (key) {
            case ADD -> System.out.println("TODO: ADD STATEMENTS");
            case DELETE -> System.out.println("TODO: DELETE STATEMENTS");
            case SAVE -> System.out.println("TODO: SAVE STATEMENTS");
            case QUERY -> System.out.println("TODO: QUERY STATEMENTS");
            default -> throw new UnknownPropertyException(key);
        }
    }

    public boolean run(List<Instruction> instructionList) {
        // TODO: Run the instruction.
        return false;
    }
}
