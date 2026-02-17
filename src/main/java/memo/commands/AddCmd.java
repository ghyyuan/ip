package memo.commands;

import memo.exceptions.MemoException;
import memo.storage.Storage;
import memo.tasks.Task;
import memo.tasks.TaskList;
import memo.ui.Ui;

/**
 * Represents a command to add a new task to the list.
 */

public abstract class AddCmd extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MemoException {
        Task t = createTask();

        tasks.addTask(t);
        storage.save(tasks);
        return ui.showAddedTask(t, tasks.getSize());
    }

    /**
     * Creates the specific task object (ToDo, Deadline, or Event) to be added.
     *
     * @return The new Task object.
     * @throws MemoException If task creation fails (e.g., date parsing error).
     */
    protected abstract Task createTask() throws MemoException;
}
