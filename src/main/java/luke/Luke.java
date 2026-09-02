package luke;

import java.util.Scanner;

/**
 * Runs the Luke chatbot's command-line interface.
 */

public class Luke {
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";

    private static final String COMMAND_SEPARATOR = " ";

    private static final int SPLIT_LIMIT = 2;
    private static final int HORIZONTAL_LINE_LENGTH = 60;
    private static final int MAX_TASKS = 100;

    private static final String HORIZONTAL_LINE = createHorizontalLine();

    /**
     * Starts Luke and processes commands until the user exits.
     *
     * @param args Command-line arguments; not used.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASKS];
        int taskCount = 0;

        WelcomeMessage();

        String command = input.nextLine();

        while (!command.equals("bye")) {
            System.out.println(HORIZONTAL_LINE);

            String commandWord = command.split(COMMAND_SEPARATOR, SPLIT_LIMIT)[0];

            switch (commandWord) {
            case COMMAND_LIST:
                showTaskList(tasks, taskCount);
                break;

            case COMMAND_TODO:
                tasks[taskCount] = Parser.parseTodo(command);
                taskCount++;

                showTaskAdded(tasks[taskCount - 1], taskCount);
                break;

            case COMMAND_DEADLINE:
                tasks[taskCount] = Parser.parseDeadline(command);
                taskCount++;

                showTaskAdded(tasks[taskCount - 1], taskCount);
                break;

            case COMMAND_EVENT:
                tasks[taskCount] = Parser.parseEvent(command);
                taskCount++;

                showTaskAdded(tasks[taskCount - 1], taskCount);
                break;

            case COMMAND_MARK:
                int taskNumber = Integer.parseInt(command.substring(
                        COMMAND_MARK.length() + COMMAND_SEPARATOR.length()));
                Task task = tasks[taskNumber - 1];

                task.markAsDone();

                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + task);
                break;

            case COMMAND_UNMARK:
                int unmarkTaskNumber = Integer.parseInt(command.substring(
                        COMMAND_UNMARK.length() + COMMAND_SEPARATOR.length()));
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

        ByeMessage();
    }

    private static void WelcomeMessage() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Luke\n" + getLogo());
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void ByeMessage() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }


    private static void showTaskList(Task[] tasks, int taskCount) {
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
    }

    private static void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
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
