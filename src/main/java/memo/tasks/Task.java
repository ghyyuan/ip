package memo.tasks;

import memo.exceptions.MemoException;

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

    public void addTag(String tag) throws MemoException {
        if (this.tags.contains(tag)) {
            throw new MemoException("Tag " + tag + " already exists QaQ");
        } else if (tag.trim().isEmpty()) {
            throw new MemoException("Tag should not be empty QaQ");
        }
        this.tags.add(tag);
    }

    /**
     * Retrieves the string representation of all tags associated with this task.
     * Each tag is prefixed with a '#' symbol and separated by spaces.
     *
     * @return A string containing all formatted tags, or an empty string if there are no tags.
     */
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
        return String.format("[%s] %s", getStatus(), description);
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
