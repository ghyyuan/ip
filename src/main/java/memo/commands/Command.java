package memo.commands;

import memo.exceptions.MemoException;
import memo.storage.Storage;
import memo.tasks.TaskList;
import memo.ui.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MemoException;

    public boolean isExit() {
        return false;
    }
}