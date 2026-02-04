public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MemoException {
        Task removed = tasks.deleteTask(index);
        ui.showRemove(removed, tasks.getSize());
        storage.save(tasks);
    }
}