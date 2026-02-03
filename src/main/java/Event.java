import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, String from, String to) throws MemoException {
        super(description);

        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (Exception e) {
            throw new MemoException("Please enter in yyyy-mm-dd format :O");
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                from.format(DateTimeFormatter.ofPattern("MMM d yyyy")), to.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String storeForm() {
        return String.format("E | %s | %s | %s", super.storeForm(),
                this.from, this.to);
    }
}