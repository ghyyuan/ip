package memo;

import memo.commands.Command;
import memo.exceptions.MemoException;
import memo.parser.Parser;
import memo.storage.Storage;
import memo.tasks.TaskList;
import memo.ui.Ui;

/**
 * Main class for the Memo application.
 * Initializes the application components (Ui, Storage, TaskList) and runs the main loop.
 */

public class Memo {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit = false;

    /**
     * Constructs a Memo instance with the specified file path for storage.
     * Attempts to load existing tasks from the file; creates a new list if loading fails.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Memo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load(ui));
        } catch (MemoException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public String getGreeting() {
        return ui.showWelcome();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            this.isExit = c.isExit();
            return c.execute(tasks, ui, storage);
        } catch (MemoException e) {
            return ui.showError(e.getMessage());
        }
    }

    public boolean isExit() {
        return isExit;
    }
}
