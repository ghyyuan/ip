public class AddDeadlineCmd extends AddCommand {
    private String desc;
    private String by;

    public AddDeadlineCmd(String desc, String by) {
        this.desc = desc;
        this.by = by;
    }

    @Override
    public Task createTask() throws MemoException {
        return new Deadline(desc, by);
    }
}