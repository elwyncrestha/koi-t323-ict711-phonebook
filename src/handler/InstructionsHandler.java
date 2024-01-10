package handler;

import static constant.AppConstant.ADD;
import static constant.AppConstant.DELETE;
import static constant.AppConstant.INSTRUCTION_FILE_NAME;
import static constant.AppConstant.QUERY;
import static constant.AppConstant.SAVE;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exception.UnknownPropertyException;
import model.Instruction;
import specs.FileHandlerImpl;

/**
 * An implementation class that handles phone book instructions.
 * Note: This class is only used for handling the instructions, not the data.
 * Check {@link PhoneBookDataHandler} for the data.
 */
public class InstructionsHandler extends FileHandlerImpl<List<Instruction>> {

    public InstructionsHandler() {
        super(INSTRUCTION_FILE_NAME);
    }

    private static void mapContentToInstruction(Instruction instruction, String content)
        throws UnknownPropertyException {
        String[] keyValue = content.split(" ", 2);
        String key = keyValue[0];
        String value = keyValue[1].trim();

        switch (key) {
            case ADD -> System.out.println("TODO: ADD STATEMENTS");
            case DELETE -> System.out.println("TODO: DELETE STATEMENTS");
            case SAVE -> System.out.println("TODO: SAVE STATEMENTS");
            case QUERY -> System.out.println("TODO: QUERY STATEMENTS");
            default -> throw new UnknownPropertyException(key);
        }
    }

    @Override
    public List<Instruction> deserialize(File file)
        throws FileNotFoundException, DateTimeParseException, UnknownPropertyException {
        List<Instruction> instructionList = new ArrayList<>();

        try (final Scanner scanner = new Scanner(file)) {

            Instruction instruction = null;
            boolean hasNextLine = scanner.hasNextLine();
            while (hasNextLine) {
                String line = scanner.nextLine();

                if (instruction != null && line.trim().isEmpty()) {
                    instructionList.add(instruction);
                    instruction = null;
                    continue;
                }

                if (instruction == null) {
                    instruction = new Instruction();
                }
                mapContentToInstruction(instruction, line);

                hasNextLine = scanner.hasNextLine();
                if (!hasNextLine) {
                    instructionList.add(instruction);
                }
            }
        }
        return instructionList;
    }

    public void run(List<Instruction> instructionList) {
        // TODO: Run the instruction.
    }
}
