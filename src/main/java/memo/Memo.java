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
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);

            // 3. 执行命令
            // 【注意】这里有个大坑！
            // 以前 c.execute 会直接 System.out.println。
            // 在 GUI 里，你需要让 execute 返回字符串，或者通过某种方式捕获输出。
            // 暂时为了跑通，我们假设你还没有改 Command，先只做逻辑处理。

            c.execute(tasks, ui, storage);
            // 这里的 execute 目前是打印到控制台，GUI 上看不到。
            // 你之后需要改造 Command 让它返回 String，或者改造 Ui 让它捕获 String。

            return "Command executed! (Check your console for now)";

        } catch (MemoException e) {
            return "Error: " + e.getMessage();
        }
    }
}
