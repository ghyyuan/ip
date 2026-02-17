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

    public String showInitialization() {
        return "Initializing...Welcome to Memo ^^";
    }

    /**
     * Generates the welcome message for the user.
     *
     * @return The welcome message string.
     */
    public String showWelcome() {
        return "Hello! I'm Memo\nWhat can I do for you?";
    }

    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    public String showAddedTask(Task task, int total) {
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list",
                task.toString(), total);
    }

    /**
     * Generates a formatted string of all tasks in the given task list.
     *
     * @param tasks The TaskList containing tasks to display.
     * @return A string representation of the tasks.
     */
    public String showList(TaskList tasks) {
        if (tasks.getSize() == 0) {
            return "There is no task in the list";
        }

        StringBuilder sb = new StringBuilder();
        if (tasks.getSize() == 1) {
            sb.append("Here is the task in the list:\n");
        } else {
            sb.append("Here are the tasks in your list:\n");
        }

        for (int i = 0; i < tasks.getSize(); i++) {
            sb.append(i + 1).append(". ").append(tasks.getTask(i).toString()).append("\n");
        }

        return sb.toString().trim();
    }

    public String showMarkStatus(Task task, boolean isDone) {
        if (isDone) {
            return "Nice! I've marked this task as done:\n" + task.toString();
        } else {
            return "OK, I've marked this task as not done yet:\n" + task.toString();
        }
    }

    public String showRemove(Task task, int total) {
        return String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list",
                task.toString(), total);
    }

    public String showMatched(TaskList tasks) {
        if (tasks.getSize() == 0) {
            return "There is no matched task in the list";
        }

        StringBuilder sb = new StringBuilder();
        if (tasks.getSize() == 1) {
            sb.append("Here is the matched task in the list:\n");
        } else {
            sb.append("Here are the matched tasks in your list:\n");
        }

        for (int i = 0; i < tasks.getSize(); i++) {
            sb.append(i + 1).append(". ").append(tasks.getTask(i).toString()).append("\n");
        }
        return sb.toString().trim();
    }

    public String showError(String error) {
        return error;
    }
}
