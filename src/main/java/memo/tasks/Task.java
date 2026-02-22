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

    /**
     * Adds a specified tag to the task.
     * This method assumes the input tag has been pre-validated by the parser to be a
     * non-empty string.
     * It prevents duplicate tags and the injection of illegal storage characters.
     *
     * @param tag The tag string to be added.
     * @throws MemoException If the tag already exists, or contains illegal characters
     * (e.g., commas) that could corrupt the storage file structure.
     */
    public void addTag(String tag) throws MemoException {
        assert tag != null && !tag.trim().isEmpty() : "Tag passed to Task should not be null or empty";

        if (this.tags.contains(tag)) {
            throw new MemoException("Tag #" + tag + " already exists QaQ");
        }
        if (tag.contains(",")) {
            throw new MemoException("Tags cannot contain commas (,) QaQ");
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
