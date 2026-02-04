public abstract class AddCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MemoException {
        Task t = createTask();

        tasks.addTask(t);
        ui.showAddedTask(t, tasks.getSize());
        storage.save(tasks);
    }

    public abstract Task createTask() throws MemoException;
}