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

    /**
     * Runs the main program loop.
     * Handles user input, parses commands, executes them, and updates the UI until the exit command is received.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();

                Command c = Parser.parse(fullCommand);

                c.execute(tasks, ui, storage);

                isExit = c.isExit();
            } catch (MemoException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The entry point of the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Memo("data/text.txt").run();
    }
}