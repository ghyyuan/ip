package memo.commands;

import memo.exceptions.MemoException;
import memo.tasks.Event;
import memo.tasks.Task;

public class AddEventCmd extends AddCmd {
    private String desc, from, to;

    public AddEventCmd(String desc, String from, String to) {
        this.desc = desc;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Task createTask() throws MemoException {
        return new Event(desc, from, to);
    }
}
