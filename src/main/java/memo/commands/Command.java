package memo.commands;

import memo.exceptions.MemoException;
import memo.storage.Storage;
import memo.tasks.TaskList;
import memo.ui.Ui;

/**
 * Represents an abstract executable command.
 */

public abstract class Command {

    /**
     * Executes the command using the provided task list, UI, and storage.
     *
     * @param tasks   The list of tasks to operate on.
     * @param ui      The UI to interact with the user.
     * @param storage The storage to save changes if necessary.
     * @throws MemoException If an error occurs during execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws MemoException;

    /**
     * Checks if this command should exit the application.
     *
     * @return true if the application should exit, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
