package luke;

import java.util.Scanner;

/**
 * Runs the Luke chatbot's command-line interface.
 */
public class Luke {
    private static final int MARK_INDEX = 5;
    private static final int UNMARK_INDEX = 7;
    private static final int HORIZONTAL_LINE_LENGTH = 60;
    private static final String HORIZONTAL_LINE = createHorizontalLine();
    private static final int MAX_TASKS = 100;

    /**
     * Starts Luke and processes commands until the user exits.
     *
     * @param args Command-line arguments; not used.
     */
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

            String commandWord = command.split(" ")[0];

            switch (commandWord) {
            case "list":
                System.out.println("Here are the tasks in your list:");

                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                break;

            case "mark":
                int taskNumber = Integer.parseInt(command.substring(MARK_INDEX));
                Task task = tasks[taskNumber - 1];

                task.markAsDone();

                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + task);
                break;

            case "unmark":
                int unmarkTaskNumber = Integer.parseInt(command.substring(UNMARK_INDEX));
                Task unmarkTask = tasks[unmarkTaskNumber - 1];

                unmarkTask.markAsNotDone();

                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + unmarkTask);
                break;

            default:
                tasks[taskCount] = new Task(command);
                taskCount++;

                System.out.println("added: " + command);
                break;
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
