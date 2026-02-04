public class AddEventCmd extends AddCommand {
    private String desc, from, to;

    public AddEventCmd(String desc, String from, String to) {
        this.desc = desc;
        this.from = from;
        this.to = to;
    }

    @Override
    public Task createTask() throws MemoException {
        return new Event(desc, from, to);
    }
}