package model;

import java.util.List;

/**
 * This class is a model class to store the instruction details of the phone book app.
 */
public class Instruction {

    public InstructionType type;
    public List<String> keys;
    public Contact contact;

    public InstructionType getType() {
        return type;
    }

    public void setType(InstructionType type) {
        this.type = type;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
