import java.io.*;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class WorkWithFiles {
    private static void fileCreation(String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name of file was not given.");

        try {
            File newFile = new File(name);

            if (newFile.createNewFile())
                System.out.println("File " + name + " created.");

            else
                System.out.println("File " + name + " already exists.");

        } catch (IOException e) {
            System.out.println("An error occurred while creating a new file.");
            e.printStackTrace();
        }
    }

    private static void fileDeletion(String fileName) {
        File existingFile = new File(fileName);

        if (existingFile.exists() && existingFile.isFile()) {
            if (existingFile.delete())
                System.out.println("File " + fileName + " deleted.");
            else
                System.out.println("Failed to delete file " + fileName + ".");
        }

        else
            throw new IllegalArgumentException("File " + fileName + " does not exist.");
    }

    private static void printFilesInDirectory(String path) {
        if (path == null || path.isEmpty())
            throw new IllegalArgumentException("Path must not be empty.");

        File dir = new File(path);

        if (!dir.exists())
            throw new IllegalArgumentException("This directory does not exist.");

        File[] list = dir.listFiles();

        if (list != null) {
            for (File file : list) {
                if (file.isFile())
                    System.out.println("File: " + file.getName());

                else if (file.isDirectory())
                    System.out.println("Directory: " + file.getName());
            }
        }

        else
            throw new IllegalArgumentException("No files to be printed.");
    }

    private static void printContent(String path) {
        if (path == null || path.isEmpty())
            throw new IllegalArgumentException("Path must not be empty.");

        try (Scanner fileReader = new Scanner(new File(path))) {
            while (fileReader.hasNextLine())
                System.out.println(fileReader.nextLine());

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while printing the content of the given file.");
            e.printStackTrace();
        }
    }

    private static void truncateFile(String path) {
        if (path == null || path.isEmpty())
            throw new IllegalArgumentException("Path must not be empty.");

        File file = new File(path);

        if (!file.exists())
            throw new IllegalArgumentException("File " + path + " does not exist.");

        try (FileWriter fw = new FileWriter(file, false)) {
            fw.flush();
            System.out.println("File " + path + " truncated.");
        } catch (IOException e) {
            System.out.println("An error occurred while truncating the file.");
            e.printStackTrace();
        }
    }

    private static void appendTextToFile(String path) {
        if (path == null || path.isEmpty())
            throw new IllegalArgumentException("Path must not be empty.");

        File file = new File(path);

        if (!file.exists())
            throw new IllegalArgumentException("File " + path + " does not exist.");

        try (Scanner scanner = new Scanner(System.in);
             FileWriter fileWriter = new FileWriter(file, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            System.out.print("Enter text to append: ");
            String text = scanner.nextLine();

            if (text.isEmpty())
                throw new IllegalArgumentException("No text to append.");

            bufferedWriter.write('\n' + text);
            System.out.println("Text appended successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while appending text to file.");
            e.printStackTrace();
        }
    }

    public static void zipFiles(String[] srcFiles, String zipFilePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            byte[] buffer = new byte[1024];

            for (String srcFile : srcFiles) {
                File fileToZip = new File(srcFile);

                try (FileInputStream fis = new FileInputStream(fileToZip)) {
                    zos.putNextEntry(new ZipEntry(fileToZip.getName()));
                    int length;

                    while ((length = fis.read(buffer)) > 0)
                        zos.write(buffer, 0, length);

                    zos.closeEntry();
                }
            }
            System.out.println("Files zipped successfully.");
        }
    }

    public static void unzip(String zipFilePath, String destFolder) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry;
            byte[] buffer = new byte[1024];

            while ((entry = zis.getNextEntry()) != null) {
                File newFile = new File(destFolder + File.separator + entry.getName());

                if (entry.isDirectory())
                    newFile.mkdirs();

                else {
                    new File(newFile.getParent()).mkdirs();

                    try (FileOutputStream fos = new FileOutputStream(newFile)) {
                        int length;

                        while ((length = zis.read(buffer)) > 0)
                            fos.write(buffer, 0, length);
                    }
                }
            }
            System.out.println("Unzipping completed.");
        }
    }

    private static void writeInTasksTxt(String operation, String fileName) {
        File file = new File("/Users/vanessa.pashova/Desktop/tasks.txt");

        try (FileWriter fileWriter = new FileWriter(file, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            bufferedWriter.write(operation + "(" + fileName + ")\n");
            System.out.println("Operation logged successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the tasks file.");
            e.printStackTrace();
        }
    }

    private static void execution() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter command (1 - 8): ");
            String command = scanner.nextLine();

            try {
                switch (command) {
                    case "1":
                        fileCreation("/Users/vanessa.pashova/Desktop/newFile.txt");
                        writeInTasksTxt("fileCreation", "newFile.txt");
                        break;

                    case "2":
                        fileDeletion("/Users/vanessa.pashova/Desktop/newFile.txt");
                        writeInTasksTxt("fileDeletion", "newFile.txt");
                        break;

                    case "3":
                        printFilesInDirectory("/Users/vanessa.pashova/Desktop/");
                        writeInTasksTxt("printFilesInDirectory", "Desktop");
                        break;

                    case "4":
                        printContent("/Users/vanessa.pashova/Desktop/newFile.txt");
                        writeInTasksTxt("printContent", "newFile.txt");
                        break;

                    case "5":
                        truncateFile("/Users/vanessa.pashova/Desktop/newFile.txt");
                        writeInTasksTxt("truncateFile", "newFile.txt");
                        break;

                    case "6":
                        appendTextToFile("/Users/vanessa.pashova/Desktop/newFile.txt");
                        writeInTasksTxt("appendTextToFile", "newFile.txt");
                        break;

                    case "7":
                        zipFiles(new String[]{"/Users/vanessa.pashova/Desktop/newFile.txt"}, "/Users/vanessa.pashova/Desktop/compressed.zip");
                        writeInTasksTxt("zipFiles", "compressed.zip");
                        break;

                    case "8":
                        unzip("/Users/vanessa.pashova/Desktop/compressed.zip", "/Users/vanessa.pashova/Desktop/unzipped/");
                        writeInTasksTxt("unzip", "compressed.zip");
                        break;

                    default:
                        System.out.println("Invalid command.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        execution();
    }
}
