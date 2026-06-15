import java.io.*;
import java.util.Scanner;

public class Main {

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
                        readFile();
                        break;
                    case 3:
                        readRange();
                        break;
                    case 4:
                        insertLine();
                        break;
                    case 5:
                        exitEditor();
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
        System.out.println("1 - Додати декілька рядків");
        System.out.println("2 - Прочитати увесь файл");
        System.out.println("3 - Вивести діапазон рядків");
        System.out.println("4 - Вставити рядок у вибране місце");
        System.out.println("5 - Вийти");
        System.out.print("Ваш вибір: ");
    }

    public static void writeMultipleLines() {
        try {
            System.out.print("Скільки рядків додати: ");
            int count = scanner.nextInt();
            scanner.nextLine();

            int lineNumber = countLines() + 1;

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                for (int i = 0; i < count; i++) {
                    System.out.print(lineNumber + " | ");
                    String line = scanner.nextLine();
                    writer.write(line);
                    writer.newLine();
                    lineNumber++;
                }
            }

            System.out.println("Запис завершено.");

        } catch (IOException e) {
            System.out.println("Помилка запису.");
        }
    }

    public static void readFile() {

        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("Файл ще не створено.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            int number = 1;

            while ((line = reader.readLine()) != null) {
                System.out.println(number + " | " + line);
                number++;
            }

        } catch (IOException e) {
            System.out.println("Помилка читання.");
        }
    }

    public static void readRange() {

        try {
            System.out.print("Початковий рядок: ");
            int start = scanner.nextInt();

            System.out.print("Кінцевий рядок: ");
            int end = scanner.nextInt();
            scanner.nextLine();

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

                String line;
                int number = 1;

                while ((line = reader.readLine()) != null) {

                    if (number >= start && number <= end) {
                        System.out.println(number + " | " + line);
                    }

                    number++;
                }

            }

        } catch (IOException e) {
            System.out.println("Помилка читання.");
        }
    }

    public static void insertLine() {

        int totalLines = countLines();

        if (totalLines == 0) {
            System.out.println("Файл порожній.");
            return;
        }

        try {

            String[] lines = new String[totalLines];

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

                String line;
                int i = 0;

                while ((line = reader.readLine()) != null) {
                    lines[i] = line;
                    i++;
                }
            }

            System.out.print("У який рядок вставити текст: ");
            int position = scanner.nextInt();
            scanner.nextLine();

            if (position < 1 || position > totalLines) {
                System.out.println("Невірний номер рядка.");
                return;
            }

            System.out.print(position + " | ");
            String newLine = scanner.nextLine();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

                for (int i = 0; i < position - 1; i++) {
                    writer.write(lines[i]);
                    writer.newLine();
                }

                writer.write(newLine);
                writer.newLine();

                for (int i = position - 1; i < totalLines; i++) {
                    writer.write(lines[i]);
                    writer.newLine();
                }
            }

            System.out.println("Рядок вставлено.");

        } catch (IOException e) {
            System.out.println("Помилка.");
        }
    }

    public static int countLines() {

        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            while (reader.readLine() != null) {
                count++;
            }

        } catch (IOException e) {
        }

        return count;
    }

    public static void exitEditor() {
        System.out.println("Вихід з редактора...");
    }
}
