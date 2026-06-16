import java.util.ArrayList;
import java.util.Scanner;

class Task {
    int taskId;
    String title;
    String description;

    Task(int taskId, String title, String description) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
    }
}

public class TaskManager {
    static ArrayList<Task> tasks = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void createTask() {
        int taskId = tasks.size() + 1;

        System.out.print("Enter task title: ");
        String title = scanner.nextLine();

        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        tasks.add(new Task(taskId, title, description));
        System.out.println("Task created successfully!");
    }

    public static void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("\n----- Task List -----");
            for (Task task : tasks) {
                System.out.println("Task ID: " + task.taskId);
                System.out.println("Title: " + task.title);
                System.out.println("Description: " + task.description);
                System.out.println("--------------------");
            }
        }
    }

    public static void updateTask() {
        displayTasks();

        if (tasks.isEmpty()) {
            return;
        }

        System.out.print("Enter task ID to update: ");
        int taskId = scanner.nextInt();
        scanner.nextLine();

        for (Task task : tasks) {
            if (task.taskId == taskId) {
                System.out.print("Enter new task title: ");
                task.title = scanner.nextLine();

                System.out.print("Enter new task description: ");
                task.description = scanner.nextLine();

                System.out.println("Task updated successfully!");
                return;
            }
        }

        System.out.println("Task not found.");
    }

    public static void deleteTask() {
        displayTasks();

        if (tasks.isEmpty()) {
            return;
        }

        System.out.print("Enter task ID to delete: ");
        int taskId = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).taskId == taskId) {
                tasks.remove(i);
                System.out.println("Task deleted successfully!");
                return;
            }
        }

        System.out.println("Task not found.");
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Task Manager Application =====");
            System.out.println("1. Create Task");
            System.out.println("2. Display Tasks");
            System.out.println("3. Update Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                createTask();
            } else if (choice == 2) {
                displayTasks();
            } else if (choice == 3) {
                updateTask();
            } else if (choice == 4) {
                deleteTask();
            } else if (choice == 5) {
                System.out.println("Thank you for using Task Manager Application.");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
