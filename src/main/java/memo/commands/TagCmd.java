package memo.commands;

import memo.exceptions.MemoException;
import memo.storage.Storage;
import memo.tasks.Task;
import memo.tasks.TaskList;
import memo.ui.Ui;

public class TagCmd extends Command {
    private int index;
    private String tag;

    public TagCmd(int index, String tag) {
        this.index = index;
        this.tag = tag;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MemoException {
        tasks.validateIndex(index);
        Task task = tasks.getTask(index);
        task.addTag(tag);

        storage.save(tasks); // 存盘
        return ui.showTagged(task); // 通知UI显示
    }
}
