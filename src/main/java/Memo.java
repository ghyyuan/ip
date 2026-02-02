import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class Memo {
    public static void main(String[] args) {
        List<Task> list = new ArrayList<>();
        UI ui = new UI();

        File f = new File("data/text.txt");
        // System.out.println(f.getAbsolutePath());

        try {
            // if the parent directory doesn't exist
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }

            // if the file doesn't exist
            if (!f.exists()) {
                f.createNewFile();
                ui.showInitialization();
            }

            Scanner fileReader = new Scanner(f);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                // System.out.println("DEBUG: Reading line [" + line + "] Length: " + line.length());
                try {
                    Task loadedTask = Parser.fromStoreForm(line);
                    list.add(loadedTask);
                } catch (MemoException e) {
                    System.out.println("Skipping corrupted line...");
                }
            }

        } catch (IOException e) {
            ui.showError("Error with reading file :O");
        }

        Scanner sc = new Scanner(System.in);

        ui.showWelcome();

        while (true) {
            String input = sc.nextLine().trim();

            String[] inputs = input.split(" ", 2);
            Command cmd = Command.fromString(inputs[0]);

            try {
                if (cmd == Command.BYE) {
                    ui.showBye();
                    break;
                }

                switch (cmd) {
                    case LIST:
                        ui.showList(list);
                        break;

                    case MARK:
                    case UNMARK:
                        if (inputs.length != 2) {
                            throw new MemoException("Command is not complete ><");
                        }
                        // check length BEFORE parsing
                        int index = Integer.parseInt(inputs[1]) - 1;
                        if (!(index >= 0 && index < list.size())) {
                            throw new MemoException("Please enter a valid index within the list :O");
                        }

                        boolean isDone = cmd == Command.MARK;
                        list.get(index).changeStatus(isDone);
                        ui.showMarkStatus(list.get(index), isDone);
                        save(f, list, ui);
                        break;

                    case DELETE:
                        if (inputs.length != 2) {
                            throw new MemoException("Command is not complete ><");
                        }
                        // check length BEFORE parsing
                        int i = Integer.parseInt(inputs[1]) - 1;
                        if (!(i >= 0 && i < list.size())) {
                            throw new MemoException("Please enter a valid index within the list :O");
                        }

                        Task curr = list.get(i);
                        list.remove(i);
                        ui.showRemove(curr, list.size());
                        save(f, list, ui);
                        break;

                    case TODO:
                        if (inputs.length != 2) {
                            throw new MemoException("Please enter what you gonna do =(");
                        }
                        Task t = new ToDo(inputs[1]);
                        list.add(t);
                        ui.showAddedTask(t, list.size());
                        save(f, list, ui);
                        break;

                    case DEADLINE:
                        if (inputs.length != 2) {
                            throw new MemoException("Please enter what you gonna do with time =(");
                        }
                        String[] ddl = inputs[1].split(" /by ");
                        if (ddl.length != 2) {
                            throw new MemoException("Please specify the ddl with /by :(");
                        }
                        Deadline d = new Deadline(ddl[0], ddl[1]);
                        list.add(d);
                        ui.showAddedTask(d, list.size());
                        save(f, list, ui);
                        break;

                    case EVENT:
                        if (inputs.length != 2) {
                            throw new MemoException("Please enter what you gonna do with time =(");
                        }
                        String[] str1 = inputs[1].split(" /from ");
                        if (str1.length != 2) {
                            throw new MemoException("Please specify the event with /from :(");
                        }
                        String[] str2 = str1[1].split(" /to ");
                        if (str2.length != 2) {
                            throw new MemoException("Please specify the event with /to:(");
                        }
                        Event e = new Event(str1[0], str2[0], str2[1]);
                        list.add(e);
                        ui.showAddedTask(e, list.size());
                        save(f, list, ui);
                        break;

                    case UNKNOWN:
                        ui.showError("What does that mean ><");
                        break;
                }

            } catch (MemoException e) {
                ui.showError(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showError("Please enter a valid number ><");
            }
        }
    }

    // save the list content
    private static void save(File f, List<Task> list, UI ui) {
        try (FileWriter fw = new FileWriter(f)) {
            for (Task t : list) {
                fw.write(t.storeForm() + "\n");
            }
        } catch (IOException e) {
            ui.showError("Could not save tasks to storage! :O");
        }
    }
}
