package memo.parser;

import memo.commands.*;
import memo.exceptions.MemoException;
import memo.tasks.Deadline;
import memo.tasks.Event;
import memo.tasks.Task;
import memo.tasks.ToDo;

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
        String[] inputs = fullCmd.split(" ", 2);
        CommandType type = CommandType.fromString(inputs[0]);

        boolean isCmdNeedingArgs = (type != CommandType.LIST)
                && (type != CommandType.BYE)
                && (type != CommandType.UNKNOWN);

        if (isCmdNeedingArgs && inputs.length < 2) {
            throw new MemoException("The description of a " + type + " cannot be empty! =(");
        }

        try {
            return switch (type) {
                case LIST -> new ListCmd();
                case TODO -> new AddTodoCmd(inputs[1]);
                case DEADLINE -> prepareDeadline(inputs[1]);
                case EVENT -> prepareEvent(inputs[1]);
                case DELETE -> new DeleteCmd(Integer.parseInt(inputs[1]) - 1);
                case MARK -> new ChangeStatusCmd(Integer.parseInt(inputs[1]) - 1, true);
                case UNMARK -> new ChangeStatusCmd(Integer.parseInt(inputs[1]) - 1, false);
                case BYE -> new ExitCmd();
                case FIND -> new FindCmd(inputs[1]);
                case UNKNOWN -> throw new MemoException("I don't know what that means ><");
            };
        } catch (NumberFormatException e) {
            throw new MemoException("Please put in number format ><");
        }
    }

    private static Command prepareDeadline(String args) throws MemoException {
        String[] parts = args.split(" /by ");
        if (parts.length < 2) {
            throw new MemoException("Please specify the deadline with /by :(");
        }
        return new AddDeadlineCmd(parts[0], parts[1]);
    }

    private static Command prepareEvent(String args) throws MemoException {
        String[] fromParts = args.split(ARG_FROM);
        if (fromParts.length < 2) {
            throw new MemoException("Please specify /from for your event :(");
        }
        String[] toParts = fromParts[1].split(ARG_TO);
        if (toParts.length < 2) {
            throw new MemoException("Please specify /to for your event :(");
        }
        return new AddEventCmd(fromParts[0], toParts[0], toParts[1]);
    }
}
