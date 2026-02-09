package memo.commands;

import memo.exceptions.MemoException;
import memo.storage.Storage;
import memo.tasks.Task;
import memo.tasks.TaskList;
import memo.ui.Ui;

public class DeleteCmd extends Command {
    private int index;

    public DeleteCmd(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MemoException {
        Task removed = tasks.deleteTask(index);
        ui.showRemove(removed, tasks.getSize());
        storage.save(tasks);
    }
}
