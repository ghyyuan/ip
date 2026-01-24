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

    public void changeStatus() {
        isDone = !isDone;
    }
}
