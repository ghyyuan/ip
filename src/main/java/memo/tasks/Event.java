package memo.tasks;

import memo.exceptions.MemoException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, String from, String to) throws MemoException {
        super(description);

        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
            if (this.to.isBefore(this.from)) {
                throw new MemoException("woo...The end date cannot be earlier than the start date ><");
            }
        } catch (DateTimeParseException e) {
            throw new MemoException("Please enter in yyyy-mm-dd format :O");
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                from.format(DateTimeFormatter.ofPattern("MMM d yyyy")), to.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String toStoreForm() {
        return String.format("E | %s | %s | %s", super.toStoreForm(),
                this.from, this.to);
    }
}
