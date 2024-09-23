package Messenger;

import Users.User;
import SchoolDetails.Role;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MessageFileManager {

    private static final String FILE_NAME = "messages.txt";

    public static void saveMessageToFile(User sender, User receiver, Messege message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(sender.getRole() + ": " + sender.getFirstName() + " " + sender.getSurname() + " -> " +
                    receiver.getRole() + ": " + receiver.getFirstName() + " " + receiver.getSurname());
            writer.newLine();

            writer.write(message.getSubject());
            writer.newLine();

            writer.write(message.getMessege());
            writer.newLine();

            writer.newLine();
        } catch (IOException e) {
            System.out.println("--- Error while saving the message to file ---");
            e.printStackTrace();
        }
    }

    public static List<Messege> loadMessagesFromFile(User user) {
        List<Messege> messages = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("->")) {
                    String[] parts = line.split("->");
                    String[] senderParts = parts[0].trim().split(":");
                    String[] receiverParts = parts[1].trim().split(":");

                    String senderRoleString = senderParts[0].trim();
                    String senderName = senderParts[1].trim();
                    String receiverRoleString = receiverParts[0].trim();
                    String receiverName = receiverParts[1].trim();

                    String subject = reader.readLine().trim();
                    String messageContent = reader.readLine().trim();

                    Role senderRole = Role.valueOf(senderRoleString.toUpperCase());
                    Role receiverRole = Role.valueOf(receiverRoleString.toUpperCase());

                    String[] senderNameParts = senderName.split(" ");
                    String senderFirstName = senderNameParts[0].trim();
                    String senderLastName = senderNameParts[1].trim();

                    String[] receiverNameParts = receiverName.split(" ");
                    String receiverFirstName = receiverNameParts[0].trim();
                    String receiverLastName = receiverNameParts[1].trim();

                    if (receiverFirstName.equals(user.getFirstName()) && receiverLastName.equals(user.getSurname()))
                        messages.add(new Messege(subject, messageContent, senderRole, senderFirstName, senderLastName, receiverRole, receiverFirstName, receiverLastName));
                }

                reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return messages;
    }

    public static List<Messege> loadSentMessagesFromFile(User user) {
        List<Messege> sentMessages = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains("->")) {
                    String[] parts = line.split("->");
                    String[] senderParts = parts[0].trim().split(":");
                    String senderRoleString = senderParts[0].trim();
                    String senderName = senderParts[1].trim();

                    String subject = reader.readLine().trim();
                    String messageContent = reader.readLine().trim();

                    Role senderRole = Role.valueOf(senderRoleString.toUpperCase());

                    String[] senderNameParts = senderName.split(" ");
                    String senderFirstName = senderNameParts[0].trim();
                    String senderLastName = senderNameParts[1].trim();

                    if (senderFirstName.equals(user.getFirstName()) && senderLastName.equals(user.getSurname())) {
                        String[] receiverParts = parts[1].trim().split(":");
                        String receiverRoleString = receiverParts[0].trim();
                        String receiverName = receiverParts[1].trim();

                        Role receiverRole = Role.valueOf(receiverRoleString.toUpperCase());

                        String[] receiverNameParts = receiverName.split(" ");
                        String receiverFirstName = receiverNameParts[0].trim();
                        String receiverLastName = receiverNameParts[1].trim();

                        sentMessages.add(new Messege(subject, messageContent, senderRole, senderFirstName, senderLastName, receiverRole, receiverFirstName, receiverLastName));
                    }
                }

                reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sentMessages;
    }
}
