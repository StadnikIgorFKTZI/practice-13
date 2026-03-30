import java.io.*;
import java.util.Scanner;

public class SimpleTextEditor {

    static String fileName = "text.txt";
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int choice = 0;

        while (choice != 5) {
            showMenu();

            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        writeMultipleLines();
                        break;
                    case 2:
                        readall();
                        break;
                    case 3:
                        readRange();
                        break;
                    case 4:
                        insertAtLine();
                        break;
                    case 5:
                        System.out.println("вихід...")
                        break;
                    default:
                        System.out.println("Невірний пункт меню.");
                }

            } catch (Exception e) {
                System.out.println("Помилка введення.");
                scanner.nextLine();
            }
        }
    }

    public static void showMenu() {
        System.out.println("\n--- Текстовий редактор ---");
        System.out.println("1 -  Додати декілька рядків");
        System.out.println("2 - Прочитати увесь вміст файлу");
        System.out.println("3 - Прочитати діапозон");
        System.out.println("4 - Вставити у рядок");
        System.out.println("5 - Вийти з програми")
        System.out.print("Ваш вибір: ");
    }

    public static void writeToFile() {
        try (BufferedWriter writer = new BufferedWriter(newFileWriter(fileName, true))) {

            int LineNumber = countLines();

            while (true) {
                String line = scanner.nextLine();
                if (line.equalsIgnoreCase("стопє")) {
                    break;
                }

                LineNumber++;
                writer.write(line);
                writer.newLine();

                System.out.println("Записано у рядок №" + LineNumber);
            }
        }

        catch (IOException e) {
            System.out.println("Помилка запису у файл.");
        }

    }

    public static void readAll() {
        try (BufferedWriter writer = new BufferedWriter(newFileWriter(fileName))) {
            String line;
            int count = 1;

            if (!file.exists()) {
                System.out.println("Файл ще не створено.");
                return;
            }
            while ((line = reader.readLine()) != null) {
                System.out.println(count + ": " + line);
                count++;
            }
        } catch (IOException e) {
            System.out.println("Помилка читання файлу.");
        }
    }

    public static void readRange() {
        System.out.print("Початковий рядок: ");
        int start = scanner.nextInt();
        System.out.print("Кінцевий рядок: ");
        int end = scanner.nextInt();
        scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(newFileWriter(fileName))) {
            String line;
            int count = 1;

            if (!file.exists()) {
                System.out.println("Файл ще не створено.");
                return;
            }

            while ((line = reader.readLine()) != null) {
                if (count >= start && count <= end) {
                    System.out.println(count + ": " + line);
                } count++;
        }
    }

    public static void insertAtLine() {

        }
        try (BufferedWriter writer = new BufferedWriter(newFileWriter(fileName))) {
            String line;
            int count = 1;

            if (!file.exists()) {
                System.out.println("Файл ще не створено.");
                return;
            }
    }

    public static void exitEditor() {
        System.out.println("Вихід з редактора...");
    }
}