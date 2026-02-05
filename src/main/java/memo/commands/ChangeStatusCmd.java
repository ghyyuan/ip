package memo.commands;

import memo.storage.Storage;
import memo.tasks.Task;
import memo.tasks.TaskList;
import memo.ui.Ui;
import memo.exceptions.MemoException;

public class ChangeStatusCmd extends Command {
    private int index;
    private boolean isDone;

    public ChangeStatusCmd(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MemoException {
        tasks.validateIndex(index);
        Task task = tasks.getTask(index);
        task.changeStatus(isDone);
        ui.showMarkStatus(task, isDone);
        storage.save(tasks);
    }
}