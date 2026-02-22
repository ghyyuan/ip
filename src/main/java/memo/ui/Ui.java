package memo.ui;

import memo.tasks.Task;
import memo.tasks.TaskList;

/**
 * Handles all user interactions, including reading input and displaying messages.
 */

public class Ui {
    /**
     * Generates the welcome message for the user.
     *
     * @return The welcome message string.
     */
    public String showWelcome() {
        return "Hello! I'm Memo\nWhat can I do for you? :P";
    }

    public String showBye() {
        return "Bye. Hope to see you again soon! TT";
    }

    /**
     * Returns a formatted message confirming that a task has been successfully added.
     *
     * @param task  The task that was added.
     * @param total The total number of tasks in the list after adding.
     * @return A formatted confirmation message including the added task and the new total.
     */
    public String showAddedTask(Task task, int total) {
        assert task != null : "Task to be shown should not be null";
        assert total > 0 : "Total tasks should be strictly positive after adding";
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list",
                task, total);
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

    /**
     * Returns a formatted message indicating the updated completion status of a task.
     *
     * @param task   The task whose status was changed.
     * @param isDone True if the task was marked as done, false if marked as not done.
     * @return A formatted message displaying the task and its new status.
     */
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

    /**
     * Returns a formatted string representing a list of tasks that match a search query.
     * Handles empty lists, single matches, and multiple matches with appropriate phrasing.
     *
     * @param tasks The TaskList containing the matched tasks to display.
     * @return A formatted string listing all matching tasks, or a message indicating no matches were found.
     */
    public String showMatched(TaskList tasks) {
        if (tasks.getSize() == 0) {
            return "There is no matched task in the list :o";
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

    public String showTagged(Task task) {
        return "Nice! I've tagged this task:\n  " + task.toString();
    }
}
