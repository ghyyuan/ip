package memo.commands;

import memo.storage.Storage;
import memo.tasks.TaskList;
import memo.ui.Ui;

public class FindCmd extends Command {

    public String query;
    public FindCmd(String query) {
        this.query = query;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showMatched(tasks.findTasks(query));
    }
}
