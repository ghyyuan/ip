package memo.commands;

import memo.exceptions.MemoException;
import memo.storage.Storage;
import memo.tasks.Task;
import memo.tasks.TaskList;
import memo.ui.Ui;

public abstract class AddCmd extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MemoException {
        Task t = createTask();

        tasks.addTask(t);
        ui.showAddedTask(t, tasks.getSize());
        storage.save(tasks);
    }

    protected abstract Task createTask() throws MemoException;
}
