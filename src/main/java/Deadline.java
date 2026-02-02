public class Deadline extends Task {

    protected String due;

    public Deadline(String description, String due) {
        super(description);
        this.due = due;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), due);
    }

    @Override
    public String storeForm() {
        return String.format("D | %s | %s", super.toString(), this.due);
    }
}
