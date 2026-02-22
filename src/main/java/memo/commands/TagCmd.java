package memo.commands;

import memo.exceptions.MemoException;
import memo.storage.Storage;
import memo.tasks.Task;
import memo.tasks.TaskList;
import memo.ui.Ui;

public class TagCmd extends Command {
    private int index;
    private String newTag;

    public TagCmd(int index, String tag) {
        this.index = index;
        this.newTag = tag;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MemoException {
        tasks.validateIndex(index);
        Task task = tasks.getTask(index);
        task.addTag(newTag);

        storage.save(tasks);
        return ui.showTagged(task);
    }
}
