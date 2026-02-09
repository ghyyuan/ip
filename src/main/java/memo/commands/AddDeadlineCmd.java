package memo.commands;

import memo.exceptions.MemoException;
import memo.tasks.Deadline;
import memo.tasks.Task;

public class AddDeadlineCmd extends AddCmd {
    private String desc;
    private String by;

    public AddDeadlineCmd(String desc, String by) {
        this.desc = desc;
        this.by = by;
    }

    @Override
    protected Task createTask() throws MemoException {
        return new Deadline(desc, by);
    }
}
