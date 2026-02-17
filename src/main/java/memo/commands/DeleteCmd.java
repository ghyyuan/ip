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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MemoException {
        Task removed = tasks.deleteTask(index);
        storage.save(tasks);
        return ui.showRemove(removed, tasks.getSize());
    }
}
