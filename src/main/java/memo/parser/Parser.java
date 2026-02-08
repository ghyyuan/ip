package memo.parser;

import memo.commands.*;
import memo.exceptions.MemoException;
import memo.tasks.Deadline;
import memo.tasks.Event;
import memo.tasks.Task;
import memo.tasks.ToDo;

public class Parser {

    // This method transforms the stored line into a Task Object
    public static Task fromStoreForm(String line) throws MemoException {
        String[] parts = line.split(" \\| ");
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

    // This method parse a user command to specific action
    public static Command parse(String fullCmd) throws MemoException {
        String[] inputs = fullCmd.split(" ", 2);
        CommandType type = CommandType.fromString(inputs[0]);

        if (type != CommandType.LIST && type != CommandType.BYE && inputs.length < 2) {
            throw new MemoException("The description of a " + type + " cannot be empty! =(");
        }

        try {
            return switch (type) {
                case LIST -> new ListCommand();
                case TODO -> new AddTodoCmd(inputs[1]);

                case DEADLINE -> {
                    String[] parts = inputs[1].split(" /by ");
                    if (parts.length < 2) {
                        throw new MemoException("Please specify the deadline with /by :(");
                    }
                    yield new AddDeadlineCmd(parts[0], parts[1]);
                }

                case EVENT -> {
                    String[] fromParts = inputs[1].split(" /from ");
                    if (fromParts.length < 2) {
                        throw new MemoException("Please specify /from for your event :(");
                    }
                    String[] toParts = fromParts[1].split(" /to ");
                    if (toParts.length < 2) {
                        throw new MemoException("Please specify /to for your event :(");
                    }
                    yield new AddEventCmd(fromParts[0], toParts[0], toParts[1]);
                }

                case DELETE -> new DeleteCommand(Integer.parseInt(inputs[1]) - 1);
                case MARK -> new ChangeStatusCmd(Integer.parseInt(inputs[1]) - 1, true);
                case UNMARK -> new ChangeStatusCmd(Integer.parseInt(inputs[1]) - 1, false);
                case BYE -> new ExitCommand();
                case UNKNOWN -> throw new MemoException("I don't know what that means ><");
            };
        } catch (NumberFormatException e) {
            throw new MemoException("Please put in number format ><");
        }
    }
}