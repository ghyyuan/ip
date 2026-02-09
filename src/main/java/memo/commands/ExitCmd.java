package memo.commands;

import memo.storage.Storage;
import memo.tasks.TaskList;
import memo.ui.Ui;

public class ExitCmd extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
