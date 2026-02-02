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

    public String storeForm() {
        return String.format("%d | %s", (isDone ? 1 : 0), description);
    }
}
