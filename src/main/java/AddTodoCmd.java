public class AddTodoCmd extends AddCommand {
    private String desc;

    public AddTodoCmd(String desc) {
        this.desc = desc;
    }

    @Override
    public Task createTask() {
        return new ToDo(desc);
    }
}