package Users;

import SchoolDetails.Role;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Admin extends User {

    private HashMap<String, User> users;

    public Admin(String name, String surname, int age, String email, String password, String pn) {
        super(name, surname, age, email, password, pn, Role.ADMIN);
        this.users = new HashMap<>();
        this.setAdminAge(age);
    }

    public Admin() {
        super();
        this.users = new HashMap<>();
        this.role = Role.ADMIN;
    }

    public HashMap<String, User> getUsers() {
        return new HashMap<>(users);
    }

    public void setAdminAge(int age) {
        if (age > 24 && age < 50)
            this.age = age;

        else
            throw new IllegalArgumentException("--- Admin's age is illegal ---\n");
    }

    private void invalidRole(Role role) {
        if (role == null)
            throw new IllegalArgumentException("--- Invalid role inserted ---\n");

        if (role != Role.STUDENT && role != Role.TEACHER)
            throw new IllegalArgumentException("--- Invalid role inserted ---\n");
    }

    private void validateUser(Role role, User user) {
        if (role == Role.STUDENT && !(user instanceof Student))
            throw new IllegalArgumentException("--- Invalid user definition ---\n");

        if (role == Role.TEACHER && !(user instanceof Teacher))
            throw new IllegalArgumentException("--- Invalid user definition ---\n");
    }

    private void validateNames(String name, String lastName) {
        if (name == null || name.isEmpty() || lastName == null || lastName.isEmpty())
            throw new IllegalArgumentException("--- Invalid names given ---\n");
    }

    private int getCountOfUsersByRole(Role role) {
        invalidRole(role);

        int count = 0;
        for (Map.Entry<String, User> entry : users.entrySet()) {
            User user = entry.getValue();
            if (user.getRole() == role)
                count++;
        }

        return count;
    }

    public int getTeachersCount() {
        return getCountOfUsersByRole(Role.TEACHER);
    }

    public int getStudentsCount() {
        return getCountOfUsersByRole(Role.STUDENT);
    }

    private String generateKey(Role role, User user) {
        return "[ " + role + " : " + user.getFirstName() + " " + user.getSurname() + " ]\n";
    }

    public void addUser(Role role, User user) {
        invalidRole(role);
        validateUser(role, user);

        String key = generateKey(role, user);
        users.put(key, user);
        System.out.printf("> %s added successfully\n", role);
    }

    public User findUserByRoleAndName(Role role, String firstName, String lastName) {
        invalidRole(role);
        validateNames(firstName, lastName);

        for (Map.Entry<String, User> entry : users.entrySet()) {
            User user = entry.getValue();

            if (user.getRole() == role && user.getFirstName().equals(firstName) && user.getSurname().equals(lastName))
                return user;
        }

        return null;
    }

    public void removeUser(Role role, String name, String lastName) {
        invalidRole(role);
        validateNames(name, lastName);

        User userToRemove = findUserByRoleAndName(role, name, lastName);

        if (userToRemove != null) {
            users.remove(generateKey(role, userToRemove));
            System.out.println("> User removed successfully\n");
        }

        else
            throw new IllegalArgumentException("--- User does not exist ---\n");
    }

    public void updateUser(Role role, String firstName, String lastName) {
        invalidRole(role);

        User user = findUserByRoleAndName(role, firstName, lastName);

        if (user == null)
            throw new IllegalArgumentException("--- User does not exists ---\n");

        Scanner scanner = new Scanner(System.in);
        System.out.println("[ What needs to be updated?\nAnswer with just a digit!\n1. Last Name\n2. Age\n3. Email\n4. Phone Number            ]");
        int answer = scanner.nextInt();
        scanner.nextLine();

        switch (answer) {
            case 1: {
                System.out.println("[ Type down the new last name ]: ");
                String newName = scanner.nextLine();
                user.setSurname(newName);
                System.out.println("> Last name successfully updated!\n");
                break;
            }

            case 2: {
                System.out.println("[ Type down the new age ]: ");
                int newAge = scanner.nextInt();
                user.setAge(newAge);
                System.out.println("> Age successfully updated!\n");
                break;
            }

            case 3: {
                System.out.println("[ Type down the new email ]: ");
                String newEmail = scanner.nextLine();
                user.setEmail(newEmail);
                System.out.println("> Email successfully updated!\n");
                break;
            }

            case 4: {
                System.out.println("[ Type down the new phone number ]: ");
                String newPhoneNumber = scanner.nextLine();
                user.setPhoneNumber(newPhoneNumber);
                System.out.println("> Phone number successfully updated!\n");
                break;
            }

            default: {
                throw new IllegalArgumentException("--- Invalid choice ---\n");
            }
        }

        scanner.close();
    }

    @Override
    public void viewProfile() {
        System.out.printf("Names: %s %s\n", this.firstName, this.surname);
        System.out.printf("Age: %d\nID: %d\n", this.age, id);
        System.out.printf("Email: %s\nPassword: N/A\n", this.email);

        System.out.println();
        System.out.println("Role: Admin\n\n");
    }
}
