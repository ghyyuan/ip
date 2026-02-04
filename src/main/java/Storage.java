import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    protected File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public ArrayList<Task> load(Ui ui) throws MemoException {
        // System.out.println(f.getAbsolutePath());
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
                    ui.showCorruptedError();
                }
            }
        } catch (IOException e) {
            throw new MemoException("I cannot read or create the file TT");
        }
        return tasks;
    }

    public void save(TaskList tl) throws MemoException {
        try (FileWriter fw = new FileWriter(file)) {
            for (int i = 0; i < tl.getSize(); i++) {
                Task t = tl.getTask(i);              
                fw.write(t.storeForm() + "\n");
            }
        } catch (IOException e) {
            throw new MemoException("Could not save tasks to storage! :O");
        }
    }
}