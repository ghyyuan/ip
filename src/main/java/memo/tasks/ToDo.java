package memo.tasks;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s %s", super.toString(),
                super.getTagsString()).trim();
    }

    @Override
    public String toStoreForm() {
        return String.format("T | %s | %s", super.toStoreForm(),
                String.join(",", tags));
    }
}
