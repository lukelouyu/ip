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

        Ui.showWelcome();

        String command = input.nextLine();

        while (!command.equals("bye")) {
            Ui.showHorizontalLine();

            String commandWord =
                    command.split(COMMAND_SEPARATOR, SPLIT_LIMIT)[0];

            switch (commandWord) {
            case COMMAND_LIST:
                Ui.showTaskList(tasks, taskCount);
                break;

            case COMMAND_TODO:
                tasks[taskCount] = Parser.parseTodo(command);
                taskCount++;

                Ui.showTaskAdded(tasks[taskCount - 1], taskCount);
                break;

            case COMMAND_DEADLINE:
                tasks[taskCount] = Parser.parseDeadline(command);
                taskCount++;

                Ui.showTaskAdded(tasks[taskCount - 1], taskCount);
                break;

            case COMMAND_EVENT:
                tasks[taskCount] = Parser.parseEvent(command);
                taskCount++;

                Ui.showTaskAdded(tasks[taskCount - 1], taskCount);
                break;

            case COMMAND_MARK:
                int taskNumber = Integer.parseInt(command.substring(
                        COMMAND_MARK.length()
                                + COMMAND_SEPARATOR.length()));

                Task task = tasks[taskNumber - 1];
                task.markAsDone();

                Ui.showTaskMarked(task);
                break;

            case COMMAND_UNMARK:
                int unmarkTaskNumber = Integer.parseInt(command.substring(
                        COMMAND_UNMARK.length()
                                + COMMAND_SEPARATOR.length()));

                Task unmarkTask = tasks[unmarkTaskNumber - 1];
                unmarkTask.markAsNotDone();

                Ui.showTaskUnmarked(unmarkTask);
                break;

            default:
                tasks[taskCount] = new Task(command);
                taskCount++;

                System.out.println("added: " + command);
                break;
            }

            Ui.showHorizontalLine();
            command = input.nextLine();
        }

        Ui.showGoodbye();
    }
}