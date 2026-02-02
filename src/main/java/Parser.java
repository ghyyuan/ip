public class Parser {
    public static Task fromStoreForm(String line) throws MemoException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new MemoException("Corrupted file line: " + line);
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        Task task = switch (type) {
            case "T" -> new ToDo(description);
            case "D" -> new Deadline(description, parts[3]);
            case "E" -> new Event(description, parts[3], parts[4]);
            default -> throw new MemoException("Unknown task type in file!");
        };

        if (isDone) {
            task.changeStatus(true);
        }
        return task;
    }
}
