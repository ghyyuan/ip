package memo.tasks;

import memo.exceptions.MemoException;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks and provides operations to manage them.
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param t The task to be added.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Deletes a task from the list at the specified index.
     *
     * @param index The zero-based index of the task to delete.
     * @return The task that was removed.
     * @throws MemoException If the index is out of bounds.
     */
    public Task deleteTask(int index) throws MemoException {
        validateIndex(index);
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the list at the specified index.
     *
     * @param index The zero-based index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Validates if the provided index is within the valid range of the task list.
     *
     * @param index The zero-based index to validate.
     * @throws MemoException If the index is < 0 or >= the size of the list.
     */
    public void validateIndex(int index) throws MemoException {
        if (index < 0 || index >= tasks.size()) {
            throw new MemoException("Please enter a valid index within the list :O");
        }
    }

    public int getSize() {
        return tasks.size();
    }
}