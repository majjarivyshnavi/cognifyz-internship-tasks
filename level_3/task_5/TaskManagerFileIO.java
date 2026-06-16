import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class FileTask {
    int taskId;
    String title;
    String description;

    FileTask(int taskId, String title, String description) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
    }
}

public class TaskManagerFileIO {
    static ArrayList<FileTask> tasks = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static final String FILE_NAME = "tasks.txt";

    public static void loadTasks() {
        try {
            File file = new File(FILE_NAME);

            if (!file.exists()) {
                System.out.println("No saved tasks found. Starting with empty task list.");
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");

                if (data.length == 3) {
                    int taskId = Integer.parseInt(data[0]);
                    String title = data[1];
                    String description = data[2];
                    tasks.add(new FileTask(taskId, title, description));
                }
            }

            reader.close();
        } catch (Exception e) {
            System.out.println("Error while loading tasks.");
        }
    }

    public static void saveTasks() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));

            for (FileTask task : tasks) {
                writer.write(task.taskId + "|" + task.title + "|" + task.description);
                writer.newLine();
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Error while saving tasks.");
        }
    }

    public static void createTask() {
        int taskId;

        if (tasks.isEmpty()) {
            taskId = 1;
        } else {
            taskId = tasks.get(tasks.size() - 1).taskId + 1;
        }

        System.out.print("Enter task title: ");
        String title = scanner.nextLine();

        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        tasks.add(new FileTask(taskId, title, description));
        saveTasks();

        System.out.println("Task created and saved successfully!");
    }

    public static void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("\n----- Task List -----");
            for (FileTask task : tasks) {
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

        for (FileTask task : tasks) {
            if (task.taskId == taskId) {
                System.out.print("Enter new task title: ");
                task.title = scanner.nextLine();

                System.out.print("Enter new task description: ");
                task.description = scanner.nextLine();

                saveTasks();
                System.out.println("Task updated and saved successfully!");
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
                saveTasks();
                System.out.println("Task deleted and file updated successfully!");
                return;
            }
        }

        System.out.println("Task not found.");
    }

    public static void main(String[] args) {
        loadTasks();

        while (true) {
            System.out.println("\n===== Task Manager with File Storage =====");
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
                System.out.println("Thank you for using Task Manager.");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
