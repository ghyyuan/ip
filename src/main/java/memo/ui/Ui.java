package memo.ui;

import memo.tasks.Task;
import memo.tasks.TaskList;

import java.util.Scanner;

/**
 * Handles all user interactions, including reading input and displaying messages.
 */

public class Ui {
    private static final String DIVIDER = "____________________________________________________________";

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a command line from the standard input.
     *
     * @return The user's input command with leading and trailing whitespace removed.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void showInitialization() {
        System.out.println("Initializing...Welcome to Memo ^^");
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Memo\nWhat can I do for you?");
        showLine();
    }

    public void showBye() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    public void showAddedTask(Task task, int total) {
        showLine();
        System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list\n", task, total);
        showLine();
    }

    /**
     * Displays the tasks in the given task list.
     *
     * @param tasks The TaskList containing tasks to display.
     */
    public void showList(TaskList tasks) {
        showLine();
        if (tasks.getSize() == 0) {
            System.out.println("There is no task in the list");
        } else if (tasks.getSize() == 1) {
            System.out.println("Here are the task in the list:");
        } else {
            System.out.println("Here are the tasks in your list:");
        }
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i).toString());
        }
        showLine();
    }

    public void showMarkStatus(Task task, boolean isDone) {
        showLine();
        if (isDone) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(task.toString());
        showLine();
    }

    public void showRemove(Task task, int total) {
        showLine();
        System.out.printf("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list\n", task.toString(), total);
        showLine();
    }

    public void showMatched(TaskList tasks) {
        showLine();
        if (tasks.getSize() == 0) {
            System.out.println("There is no matched task in the list");
        } else if (tasks.getSize() == 1) {
            System.out.println("Here are the matched task in the list:");
        } else {
            System.out.println("Here are the matched tasks in your list:");
        }
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i).toString());
        }
        showLine();
    }

    public void showError(String error) {
        System.out.println(error);
    }
}
