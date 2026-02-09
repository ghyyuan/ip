package memo.commands;

import memo.storage.Storage;
import memo.tasks.TaskList;
import memo.ui.Ui;

public class ListCmd extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }
}
