package memo.tasks;

/**
 * Represents a generic task with a description and completion status.
 */

public class Task  {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatus(), description);
    }

    public void changeStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of the task for storage.
     *
     * @return A formatted string suitable for saving to a file.
     */
    public String toStoreForm() {
        return String.format("%d | %s", (isDone ? 1 : 0), description);
    }
}
