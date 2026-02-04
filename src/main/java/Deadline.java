import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate due;

    public Deadline(String description, String due) throws MemoException {
        super(description);
        try {
            this.due = LocalDate.parse(due);
        } catch (Exception e) {
            throw new MemoException("Please enter in yyyy-mm-dd format :O");
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                due.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String toStoreForm() {
        return String.format("D | %s | %s", super.toStoreForm(),
                this.due);
    }
}
