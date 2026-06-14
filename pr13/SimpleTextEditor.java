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
                        writeToFile();
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
        System.out.println("\n------ Текстовий редактор ------");
        System.out.println("1 - Записати рядки у файл");
        System.out.println("2 - Прочитати весь файл");
        System.out.println("3 - Прочитати діапазон рядків");
        System.out.println("4 - Вставити рядок у потрібне місце");
        System.out.println("5 - Вийти");
        System.out.print("Ваш вибір: ");
    }


    public static void writeToFile() {

        System.out.print("Скільки рядків записати? ");
        int count = scanner.nextInt();
        scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {

            int lineNumber = countLines() + 1;

            for (int i = 0; i < count; i++) {

                System.out.print(lineNumber + ": ");
                String line = scanner.nextLine();

                writer.write(line);
                writer.newLine();

                lineNumber++;
            }

            System.out.println("Запис завершено.");

        } catch (IOException e) {
            System.out.println("Помилка запису.");
        }
    }


    public static void readFile() {

        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("Файл не існує.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            int number = 1;

            while ((line = reader.readLine()) != null) {
                System.out.println(number + ": " + line);
                number++;
            }

        } catch (IOException e) {
            System.out.println("Помилка читання.");
        }
    }


    public static void readRange() {

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
                    System.out.println(number + ": " + line);
                }

                number++;
            }

        } catch (IOException e) {
            System.out.println("Помилка читання.");
        }
    }

    public static void insertLine() {

        System.out.print("У який рядок вставити? ");
        int position = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введіть текст: ");
        String newLine = scanner.nextLine();

        try {

            int count = countLines();

            String[] lines = new String[count + 1];

            int index = 0;

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

                String line;

                while ((line = reader.readLine()) != null) {
                    lines[index] = line;
                    index++;
                }
            }

            if (position < 1)
                position = 1;

            if (position > count + 1)
                position = count + 1;

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

                for (int i = 0; i < position - 1; i++) {
                    writer.write(lines[i]);
                    writer.newLine();
                }

                writer.write(newLine);
                writer.newLine();

                for (int i = position - 1; i < count; i++) {
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

        File file = new File(fileName);

        if (!file.exists())
            return 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            while (reader.readLine() != null) {
                count++;
            }

        } catch (IOException e) {
            System.out.println("Помилка.");
        }

        return count;
    }

    public static void exitEditor() {
        System.out.println("Вихід...");
    }

}
