package model;

/**
 * This class is a model class to store the instruction details of the phone book app.
 */
public class Instruction {

    private InstructionType type;
    private Contact contact;

    public InstructionType getType() {
        return type;
    }

    public void setType(InstructionType type) {
        this.type = type;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
