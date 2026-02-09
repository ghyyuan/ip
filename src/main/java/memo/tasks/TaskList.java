package memo.tasks;

import memo.exceptions.MemoException;

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

    /**
     * Finds tasks that contain the given keyword in their description.
     *
     * @param keyword The string to search for.
     * @return A list of tasks that match the keyword.
     */
    public TaskList findTasks(String keyword) {
        List<Task> found = new ArrayList<>();
        for (Task t : tasks) {
            if (t.toString().contains(keyword)) {
                found.add(t);
            }
        }
        return new TaskList(found);
    }
}
