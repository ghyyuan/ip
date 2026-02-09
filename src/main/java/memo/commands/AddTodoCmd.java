package memo.commands;

import memo.tasks.Task;
import memo.tasks.ToDo;

public class AddTodoCmd extends AddCmd {
    private String desc;

    public AddTodoCmd(String desc) {
        this.desc = desc;
    }

    @Override
    public Task createTask() {
        return new ToDo(desc);
    }
}
