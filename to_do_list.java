
import java.io.*;
import java.util.*;

public class to_do_list {
    private static final String FILE_NAME = "tasks.txt";
    private static List<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        loadTasks();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- TODO APP ---");
            System.out.println("1. View Tasks");
            System.out.println("2. Add Task");
            System.out.println("3. Remove Task");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> viewTasks();
                case 2 -> addTask(scanner);
                case 3 -> removeTask(scanner);
                case 4 -> {
                    saveTasks();
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 4);

        scanner.close();
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            System.out.println("\nYour Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    private static void addTask(Scanner scanner) {
        System.out.print("Enter task: ");
        String task = scanner.nextLine();
        tasks.add(task);
        System.out.println("Task added!");
    }

    private static void removeTask(Scanner scanner) {
        viewTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Enter task number to remove: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // consume newline
        if (index > 0 && index <= tasks.size()) {
            tasks.remove(index - 1);
            System.out.println("Task removed!");
        } else {
            System.out.println("Invalid task number!");
        }
    }

    private static void loadTasks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(line);
            }
        } catch (IOException e) {
            System.out.println("No saved tasks found.");
        }
    }

    private static void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String task : tasks) {
                writer.write(task);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to save tasks.");
        }
    }
}
