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
            String[] inputs = input.split(" "); // if unmark/mark
            System.out.println("____________________________________________________________");
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i).toString());
                }
            } else if (inputs[0].equals("mark") && inputs.length > 1) { // in case inputs[1] doesn't exist
                int index = Integer.parseInt(inputs[1]) - 1;
                if (index >= 0 && index < list.size()) {
                    list.get(index).changeStatus();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(list.get(Integer.parseInt(inputs[1]) - 1).toString());
                }
            } else if (inputs[0].equals("unmark") && inputs.length > 1) {
                int index = Integer.parseInt(inputs[1]) - 1;
                if (index >= 0 && index < list.size()) {
                    list.get(index).changeStatus();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(list.get(Integer.parseInt(inputs[1]) - 1).toString());
                }
            } else {
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
