package memo.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a generic task with a description and completion status.
 */

public class Task  {
    protected String description;
    protected boolean isDone;
    protected List<String> tags;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<>();
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public String getTagsString() {
        if (tags.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (String tag : tags) {
            sb.append("#").append(tag).append(" ");
        }
        return sb.toString().trim();
    }

    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        String tagsStr = getTagsString();
        return String.format("[%s] %s %s", getStatus(), description, tagsStr);
    }

    public void changeStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of the task for storage.
     *
     * @return A formatted string suitable for saving to a file.
     */
    public String toStoreForm() {
        return String.format("%d | %s", (isDone ? 1 : 0), description);
    }
}
