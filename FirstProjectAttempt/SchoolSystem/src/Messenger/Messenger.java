package Messenger;

import Login.UserFileManager;
import Users.Admin;
import Users.Student;
import Users.Teacher;
import Users.User;
import SchoolDetails.Role;

import java.io.*;
import java.util.*;

public class Messenger {
    private HashMap<User, List<Messege>> received;
    private HashMap<User, List<Messege>> sent;

    private ContactListOfUser contacts;

    public Messenger() {
        this.received = new HashMap<>();
        this.sent = new HashMap<>();
        this.contacts = new ContactListOfUser();
    }

    public Messenger(Messege messege, ContactListOfUser contacts) {
        this.received = new HashMap<>();
        this.sent = new HashMap<>();
        this.contacts = contacts;
    }

    public HashMap<User, List<Messege>> getReceived() {
        return new HashMap<>(received);
    }

    public HashMap<User, List<Messege>> getSent() {
        return new HashMap<>(sent);
    }

    public ContactListOfUser getContacts() {
        return this.contacts;
    }

    public void loadUserMessages(User user) {
        List<Messege> messages = MessageFileManager.loadMessagesFromFile(user);
        if (messages != null && !messages.isEmpty())
            received.put(user, messages);
    }

    public void addToReceived(Role role, User user, Messege messege) {
        validateUser(role, user);
        received.computeIfAbsent(user, k -> new ArrayList<>()).add(messege);
    }

    public void addToSent(Role role, User user, Messege messege) {
        validateUser(role, user);
        sent.computeIfAbsent(user, k -> new ArrayList<>()).add(messege);
    }

    private void validateUser(Role role, User user) {
        if (role == Role.STUDENT && !(user instanceof Student))
            throw new IllegalArgumentException("--- Invalid user definition for student ---\n");

        if (role == Role.TEACHER && !(user instanceof Teacher))
            throw new IllegalArgumentException("--- Invalid user definition for teacher ---\n");

        if (role == Role.ADMIN && !(user instanceof Admin))
            throw new IllegalArgumentException("--- Invalid user definition for admin ---\n");

    }
}
