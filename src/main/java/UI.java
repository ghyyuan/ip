import java.util.List;

public class UI {
    private static final String DIVIDER = "____________________________________________________________";

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void showInitialization() {
        System.out.println("Initializing...");
    }

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

    public void showList(List<Task> tasks) {
        showLine();
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks in the list");
        } else if (tasks.size() == 1) {
            System.out.println("Here are the task in the list:");
        } else {
            System.out.println("Here are the tasks in your list:");
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
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

    public void showError(String error) {
        System.out.println(error);
    }
}
