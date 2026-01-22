import java.util.*;

public class Memo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Memo\nWhat can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println("____________________________________________________________");
            if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i));
                }
            }else {
                list.add(input);
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
