package memo.storage;

import memo.exceptions.MemoException;
import memo.parser.Parser;
import memo.tasks.Task;
import memo.tasks.TaskList;
import memo.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the loading and saving of tasks to a file on the hard disk.
 */

public class Storage {

    protected File file;

    public Storage(String filePath) {
        assert filePath != null && !filePath.trim().isEmpty() : "File path should not be null or empty";
        this.file = new File(filePath);
    }

    /**
     * Loads tasks from the storage file.
     * If the file or directory does not exist, they will be created.
     *
     * @param ui The Ui instance to display errors if corrupted lines are found.
     * @return An ArrayList of tasks loaded from the file.
     * @throws MemoException If an I/O error occurs while reading or creating the file.
     */
    public ArrayList<Task> load(Ui ui) throws MemoException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            // if the parent directory doesn't exist
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            // if the file doesn't exist
            if (!file.exists()) {
                file.createNewFile();
                ui.showInitialization();
                return tasks;
            }

            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();

                // deal with corrupted line
                try {
                    tasks.add(Parser.fromStoreForm(line));
                } catch (MemoException e) {
                    ui.showError(e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new MemoException("I cannot read or create the file TT");
        }
        return tasks;
    }

    /**
     * Saves the current list of tasks to the storage file.
     *
     * @param tl The TaskList containing the tasks to be saved.
     * @throws MemoException If an I/O error occurs while writing to the file.
     */
    public void save(TaskList tl) throws MemoException {
        try (FileWriter fw = new FileWriter(file)) {
            for (int i = 0; i < tl.getSize(); i++) {
                Task t = tl.getTask(i);
                fw.write(t.toStoreForm() + "\n");
            }
        } catch (IOException e) {
            throw new MemoException("Could not save tasks to storage! :O");
        }
    }
}
