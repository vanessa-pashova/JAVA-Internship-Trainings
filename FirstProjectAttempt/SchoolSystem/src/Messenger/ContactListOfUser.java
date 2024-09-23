package Messenger;

import SchoolDetails.Role;
import Users.Student;
import Users.Teacher;
import Users.User;
import Users.Admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactListOfUser {
    private HashMap<Role, List<User>> community;

    public ContactListOfUser() {
        this.community = new HashMap<>();
    }

    public HashMap<Role, List<User>> getCommunity() {
        HashMap<Role, List<User>> copy = new HashMap<>();

        for (Map.Entry<Role, List<User>> entry : this.community.entrySet())
            copy.put(entry.getKey(), new ArrayList<>(entry.getValue()));

        return copy;
    }

    private void validateUser(Role role, User user) {
        if (role == Role.STUDENT && !(user instanceof Student))
            throw new IllegalArgumentException("--- Invalid user definition for student ---\n");

        if (role == Role.TEACHER && !(user instanceof Teacher))
            throw new IllegalArgumentException("--- Invalid user definition for teacher ---\n");

        if (role == Role.ADMIN && !(user instanceof Admin))
            throw new IllegalArgumentException("--- Invalid user definition for admin ---\n");
    }

    private void validateNames(String firstName, String lastName) {
        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty())
            throw new IllegalArgumentException("--- Invalid names given ---\n");
    }

    private void invalidRole(Role role) {
        if (role == null)
            throw new IllegalArgumentException("--- Invalid role inserted ---\n");

        if (!role.equals(Role.STUDENT) && !role.equals(Role.TEACHER) && !role.equals(Role.ADMIN))
            throw new IllegalArgumentException("--- Invalid role inserted ---\n");

    }

    public void addToCommunity(Role role, User user) {
        invalidRole(role);
        validateUser(role, user);
        community.computeIfAbsent(role, k -> new ArrayList<>()).add(user);
    }

    public void removeFromCommunity(Role role, User user) {
        invalidRole(role);
        validateUser(role, user);
        List<User> users = community.get(role);

        if (users != null)
            users.remove(user);
    }

    public User findUserByRoleAndName(Role role, String firstName, String lastName, String email) {
        invalidRole(role);
        validateNames(firstName, lastName);

        List<User> usersWithRole = community.get(role);
        if (usersWithRole != null) {
            for (User user : usersWithRole) {
                if (user.getFirstName().equalsIgnoreCase(firstName) &&
                        user.getSurname().equalsIgnoreCase(lastName) &&
                        user.getEmail().equalsIgnoreCase(email))

                    return user;
            }
        }

        return null;
    }
}