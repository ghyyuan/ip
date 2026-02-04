public class ChangeStatusCommand extends Command {
    private int index;
    private boolean isDone;

    public ChangeStatusCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MemoException {
        tasks.validateIndex(index); //
        Task task = tasks.getTask(index);
        task.changeStatus(isDone); //
        ui.showMarkStatus(task, isDone); //
        storage.save(tasks);
    }
}