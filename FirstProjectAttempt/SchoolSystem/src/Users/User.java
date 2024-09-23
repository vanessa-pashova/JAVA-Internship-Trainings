package Users;

import Messenger.Messenger;
import SchoolDetails.Role;

import java.util.Scanner;

public abstract class User {
    protected String firstName, surname;
    protected int age;
    protected static int id = 1000;
    protected String email, password;
    protected String phoneNumber;
    private Messenger messengerApp;
    protected Role role;

    public User(String firstName, String surname, int age, String email, String password, String phoneNumber, Role role) {
        this.setFirstName(firstName);
        this.setSurname(surname);
        idGeneration();
        this.setPhoneNumber(phoneNumber);
        this.setEmail(email);
        this.setPassword(password);
        this.setAge(age);
        this.role = role;
        this.messengerApp = new Messenger();
    }

    public User() {
        this.firstName = "";
        this.surname = "";
        id = 0;
        this.email = "";
        this.password = "";
        this.phoneNumber = "";
        this.role = null;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getSurname() {
        return this.surname;
    }

    public int getAge() {
        return this.age;
    }

    public static int getId() {
        return id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public Role getRole() {
        return this.role;
    }

    public Messenger getMessenger() {
        return this.messengerApp;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty())
            throw new IllegalArgumentException("--- First name is missing! ---\n");

        if ('A' <= firstName.charAt(0) && firstName.charAt(0) <= 'Z')
            this.firstName = firstName;

        else {
            char firstLetter = Character.toUpperCase(firstName.charAt(0));
            this.firstName = firstLetter + firstName.substring(1);
        }
    }

    public void setSurname(String surname) {
        if (surname == null || surname.isEmpty())
            throw new IllegalArgumentException("--- Surname is missing ---\n");

        if ('A' <= surname.charAt(0) && surname.charAt(0) <= 'Z')
            this.surname = surname;

        else {
            char firstLetter = Character.toUpperCase(surname.charAt(0));
            this.surname = firstLetter + surname.substring(1);
        }
    }

    public void setAge(int age) {
        if (age < 6 || age > 66)
            throw new IllegalArgumentException("Age cannot be < 6 and > 66");

        else this.age = age;
    }

    private static int idGeneration() {
        return id++;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty())
            throw new IllegalArgumentException("--- Email is not entered ---\n");

        if (!email.toLowerCase().endsWith("@gmail.com"))
            throw new IllegalArgumentException("--- Invalid email ---\n");

        else this.email = email;
    }

    public void setPassword(String password) {
        int size = password.length();

        if (size < 10)
            throw new IllegalArgumentException("--- Password must contain > 9 characters ---\n");

        else this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() != 10)
            throw new IllegalArgumentException("--- Phone number does not exist ---\n");

        else if (phoneNumber.charAt(0) != '0' || phoneNumber.charAt(1) != '8' ||
                (phoneNumber.charAt(2) != '8' && phoneNumber.charAt(2) != '7' && phoneNumber.charAt(2) != '9'))
            throw new IllegalArgumentException("--- Invalid phone number ---\n");

        else this.phoneNumber = phoneNumber;
    }

    public void setRole(Role role) {
        if (role == null)
            throw new IllegalArgumentException("--- Role cannot be null ---\n");

        this.role = role;
    }

    public abstract void viewProfile();
}
