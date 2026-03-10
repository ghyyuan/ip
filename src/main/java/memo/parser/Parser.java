package memo.parser;

import memo.commands.AddDeadlineCmd;
import memo.commands.AddEventCmd;
import memo.commands.AddTodoCmd;
import memo.commands.ChangeStatusCmd;
import memo.commands.Command;
import memo.commands.CommandType;
import memo.commands.DeleteCmd;
import memo.commands.ExitCmd;
import memo.commands.FindCmd;
import memo.commands.HelpCmd;
import memo.commands.ListCmd;
import memo.commands.TagCmd;
import memo.exceptions.MemoException;
import memo.tasks.Deadline;
import memo.tasks.Event;
import memo.tasks.Task;
import memo.tasks.ToDo;
import java.util.ArrayList;
import java.util.List;

/**
 * Parses user input and file content into executable commands and task objects.
 */

public class Parser {

    private static final String DELIMITER = " \\| ";
    private static final String ARG_BY = " /by ";
    private static final String ARG_FROM = " /from ";
    private static final String ARG_TO = " /to ";

    /**
     * Converts a line from the storage file into a Task object.
     *
     * @param line A single line of text from the storage file.
     * @return The Task object represented by the file line.
     * @throws MemoException If the line format is corrupted or the task type is unknown.
     */
    public static Task fromStoreForm(String line) throws MemoException {
        String[] parts = line.split(DELIMITER);
        if (parts.length < 3) {
            throw new MemoException("Skipping corrupted file line TT");
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        Task task = switch (type) {
            case "T" -> new ToDo(description);
            case "D" -> new Deadline(description, parts[3]);
            case "E" -> new Event(description, parts[3], parts[4]);
            default -> throw new MemoException("Unknown task type in file TT");
        };

        restoreTags(task, parts);

        if (isDone) {
            task.changeStatus(true);
        }
        return task;
    }

    /**
     * Parses the full user command and returns the corresponding Command object.
     *
     * @param fullCmd The full command string entered by the user.
     * @return A Command object corresponding to the user's input.
     * @throws MemoException If the command is unknown, incomplete, or has invalid arguments.
     */
    public static Command parse(String fullCmd) throws MemoException {
        assert fullCmd != null : "Command string should not be null";

        if (fullCmd.contains("|")) {
            throw new MemoException("Please don't use the pipe character '|' in input! ><");
        }
        String[] inputs = fullCmd.split(" ", 2);

        assert inputs.length > 0 : "Parsed input array should not be empty";
        CommandType type = CommandType.fromString(inputs[0]);

        boolean isCmdNeedingArgs = (type != CommandType.LIST)
                && (type != CommandType.BYE)
                && (type != CommandType.UNKNOWN)
                && (type != CommandType.HELP);

        if (isCmdNeedingArgs && inputs.length < 2) {
            throw new MemoException("The description of a " + type + " cannot be empty! =(");
        }

        try {
            return switch (type) {
                case LIST -> new ListCmd();
                case TODO -> prepareTodo(inputs[1]);
                case DEADLINE -> prepareDeadline(inputs[1]);
                case EVENT -> prepareEvent(inputs[1]);
                case DELETE -> new DeleteCmd(Integer.parseInt(inputs[1]) - 1);
                case MARK -> new ChangeStatusCmd(Integer.parseInt(inputs[1]) - 1, true);
                case UNMARK -> new ChangeStatusCmd(Integer.parseInt(inputs[1]) - 1, false);
                case BYE -> new ExitCmd();
                case FIND -> new FindCmd(inputs[1]);
                case TAG -> prepareTag(inputs[1]);
                case HELP -> new HelpCmd();
                case UNKNOWN -> throw new MemoException("I don't know what that means ><");
            };
        } catch (NumberFormatException e) {
            throw new MemoException("Please put in number format ><");
        }
    }

    /**
     * Prepares an {@code AddTodoCmd} by extracting inline tags and the task description
     * from the provided arguments.
     *
     * @param args The arguments string provided by the user for a todo task.
     * @return An {@code AddTodoCmd} ready for execution.
     * @throws MemoException If tag extraction or command creation fails.
     */
    private static Command prepareTodo(String args) throws MemoException {
        ParsedInput parsed = extractTags(args);
        AddTodoCmd cmd = new AddTodoCmd(parsed.cleanText());
        cmd.setTags(parsed.tags());
        return cmd;
    }

    /**
     * Prepares an {@code AddDeadlineCmd} by extracting inline tags and parsing the
     * description and deadline time separated by the "/by" keyword.
     *
     * @param args The arguments string provided by the user for a deadline task.
     * @return An {@code AddDeadlineCmd} ready for execution.
     * @throws MemoException If the "/by" keyword is missing or if description/time is empty.
     */
    private static Command prepareDeadline(String args) throws MemoException {
        ParsedInput parsed = extractTags(args);
        String[] parts = parsed.cleanText().split(ARG_BY);
        if (parts.length < 2) {
            throw new MemoException("Please specify the deadline with content + '/by' + time :(");
        }
        AddDeadlineCmd cmd = new AddDeadlineCmd(parts[0], parts[1]);
        cmd.setTags(parsed.tags());
        return cmd;
    }

    /**
     * Prepares an {@code AddEventCmd} by extracting inline tags and parsing the
     * description, start time, and end time separated by "/from" and "/to" keywords.
     *
     * @param args The arguments string provided by the user for an event task.
     * @return An {@code AddEventCmd} ready for execution.
     * @throws MemoException If keywords are missing or if any required fields are empty.
     */
    private static Command prepareEvent(String args) throws MemoException {
        ParsedInput parsed = extractTags(args);
        String[] fromParts = parsed.cleanText().split(ARG_FROM);
        if (fromParts.length < 2) {
            throw new MemoException("Please specify content + '/from' + time for your event :(");
        }
        String[] toParts = fromParts[1].split(ARG_TO);
        if (toParts.length < 2) {
            throw new MemoException("Please specify '/to' + time for your event :(");
        }
        AddEventCmd cmd = new AddEventCmd(fromParts[0], toParts[0], toParts[1]);
        cmd.setTags(parsed.tags());
        return cmd;
    }

    /**
     * Prepares a {@code TagCmd} to add a tag to an existing task based on its index.
     *
     * @param args The arguments string containing the task index and the tag name.
     * @return A {@code TagCmd} ready for execution.
     * @throws MemoException If the input format is invalid or the tag name is empty.
     */
    private static Command prepareTag(String args) throws MemoException {
        String[] parts = args.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MemoException("Please specify the task number and the tag! (e.g., tag 1 2103T) ^V^");
        }
        return new TagCmd(Integer.parseInt(parts[0]) - 1, parts[1].trim());
    }

    /**
     * Restores tags to a task by extracting them from the parsed storage string array.
     * Determines the correct index for tags based on the task type (T, D, E) and adds them to the task.
     *
     * @param task  The task to which the restored tags will be added.
     * @param parts The array of strings parsed from a single line in the storage file.
     */
    private static void restoreTags(Task task, String[] parts) throws MemoException {
        int tagIndex = switch (parts[0]) {
            case "T" -> 3;
            case "D" -> 4;
            case "E" -> 5;
            default -> -1;
        };

        if (tagIndex != -1 && parts.length > tagIndex && !parts[tagIndex].isEmpty()) {
            String[] savedTags = parts[tagIndex].split(",");
            for (String t : savedTags) {
                task.addTag(t);
            }
        }
    }

    /**
     * Extracts inline tags (words starting with '#') from the raw input string and
     * separates them from the remaining text content.
     *
     * @param rawInput The raw input string containing potential inline tags.
     * @return A {@code ParsedInput} record containing the cleaned text and a list of tags.
     * @throws MemoException If a tag is found to be empty (e.g., a lone '#').
     */
    private static ParsedInput extractTags(String rawInput) throws MemoException {
        List<String> tags = new ArrayList<>();
        StringBuilder cleanedInput = new StringBuilder();

        String[] words = rawInput.split(" +");
        for (String word : words) {
            if (word.startsWith("#")) {
                if (word.length() == 1) {
                    throw new MemoException("Tag name cannot be empty! Please specify a tag after '#' ><");
                }
                tags.add(word.substring(1));
            } else {
                cleanedInput.append(word).append(" ");
            }
        }

        return new ParsedInput(cleanedInput.toString().trim(), tags);
    }

    private record ParsedInput(String cleanText, List<String> tags) {}
}
