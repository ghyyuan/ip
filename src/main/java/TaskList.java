import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public Task deleteTask(int index) throws MemoException {
        validateIndex(index);
        return tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void validateIndex(int index) throws MemoException {
        if (index < 0 || index >= tasks.size()) {
            throw new MemoException("Please enter a valid index within the list :O");
        }
    }

    public int getSize() {
        return tasks.size();
    }
}