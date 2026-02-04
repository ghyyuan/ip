public class Memo {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();

                Command c = Parser.parse(fullCommand);

                c.execute(tasks, ui, storage);

                isExit = c.isExit();
            } catch (MemoException e) { //
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Memo("data/text.txt").run();
    }
}