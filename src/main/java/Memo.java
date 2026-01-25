import java.util.*;

public class Memo {
    public static void main(String[] args) {
        List<Task> list = new ArrayList<>();

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Memo\nWhat can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            String[] inputs = input.split(" ", 2);
            System.out.println("____________________________________________________________");
            switch (inputs[0]) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1) + ". " + list.get(i).toString());
                    }
                    break;

                case "mark":
                    int num = Integer.parseInt(inputs[1]) - 1;
                    if (num >= 0 && num < list.size()) {
                        list.get(num).changeStatus();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(list.get(Integer.parseInt(inputs[1]) - 1).toString());
                    }
                    break;

                case "unmark":
                    int ind = Integer.parseInt(inputs[1]) - 1;
                    if (ind >= 0 && ind < list.size()) {
                        list.get(ind).changeStatus();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(list.get(Integer.parseInt(inputs[1]) - 1).toString());
                    }
                    break;

                case "todo":
                    ToDo td = new ToDo(inputs[1]);
                    list.add(td);
                    System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list\n", td.toString(), list.size());
                    // print format
                    break;

                case "deadline":
                    String[] ddl = inputs[1].split(" /");
                    Deadline d = new Deadline(ddl[0], ddl[1].substring(3));
                    list.add(d);
                    System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list\n", d.toString(), list.size());
                    break;

                case "event":
                    String[] tm = inputs[1].split(" /");
                    Event e = new Event(tm[0], tm[1].substring(5), tm[2].substring(3));
                    list.add(e);
                    System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list\n", e.toString(), list.size());
                    break;

                default:
                list.add(new Task(input));
                System.out.println("added: " + input);
            }
            System.out.println("____________________________________________________________");
            input = sc.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
