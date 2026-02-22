package memo.storage;

import memo.exceptions.MemoException;
import memo.tasks.Task;
import memo.tasks.TaskList;
import memo.tasks.ToDo;
import memo.ui.Ui;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {

    @TempDir
    Path tempDir;

    @Test
    public void load_nonExistentFile_createsNewEmptyFile() throws MemoException {
        File tempFile = tempDir.resolve("non_existent_memo.txt").toFile();
        Storage storage = new Storage(tempFile.getAbsolutePath());
        Ui ui = new Ui();

        ArrayList<Task> loadedTasks = storage.load(ui);
        assertEquals(0, loadedTasks.size());

        assertTrue(tempFile.exists());
    }

    @Test
    public void saveAndLoad_validTasks_success() throws MemoException {
        File tempFile = tempDir.resolve("test_memo.txt").toFile();
        Storage storage = new Storage(tempFile.getAbsolutePath());
        Ui ui = new Ui();

        TaskList tasksToSave = new TaskList();
        tasksToSave.addTask(new ToDo("read book"));
        tasksToSave.getTask(0).addTag("fun");

        storage.save(tasksToSave);

        Storage newStorage = new Storage(tempFile.getAbsolutePath());
        ArrayList<Task> loadedTasks = newStorage.load(ui);

        assertEquals(1, loadedTasks.size());
        assertEquals("[T][ ] read book #fun", loadedTasks.get(0).toString());
    }
}
