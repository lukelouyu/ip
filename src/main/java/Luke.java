import java.util.Scanner;

public class Luke {
    private static final int HORIZONTAL_LINE_LENGTH = 60;
    private static final String HORIZONTAL_LINE = createHorizontalLine();
    private static final int MAX_TASKS = 100;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASKS];
        int taskCount = 0;

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Luke\n" + getLogo());
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        String command = input.nextLine();

        while (!command.equals("bye")) {
            System.out.println(HORIZONTAL_LINE);

            if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");

                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else {
                tasks[taskCount] = new Task(command);
                taskCount++;

                System.out.println("added: " + command);
            }

            System.out.println(HORIZONTAL_LINE);

            command = input.nextLine();
        }

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    private static String createHorizontalLine() {
        StringBuilder horizontalLine = new StringBuilder();

        for (int i = 0; i < HORIZONTAL_LINE_LENGTH; i++) {
            horizontalLine.append("_");
        }

        return horizontalLine.toString();
    }

    private static String getLogo() {
        return " _          _        \n"
                + "| |   _   _| | _____ \n"
                + "| |  | | | | |/ / _ \\\n"
                + "| |__| |_| |   <  __/\n"
                + "|_____\\__,_|_|\\_\\___|\n";
    }
}