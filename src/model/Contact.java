package model;

import java.time.LocalDate;

import utility.DateUtility;

/**
 * This class is a model class to store the contact details of the phone book app.
 */
public class Contact {

    private String name;
    private LocalDate birthday;
    private String address;
    private String phone;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        String format = """
            --------------------------------
            Name: %s
            Birthday: %s
            Address: %s
            Phone: %s
            Email: %s
            """;
        return String.format(format, name, DateUtility.toString(birthday), address, phone, email);
    }
}
