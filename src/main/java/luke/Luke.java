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
            taskCount = processCommand(command, tasks, taskCount);
            Ui.showHorizontalLine();
            command = input.nextLine();
        }

        Ui.showGoodbye();
    }

    private static int processCommand(String command, Task[] tasks,
            int taskCount) {
        String commandWord =
                command.split(COMMAND_SEPARATOR, SPLIT_LIMIT)[0];

        switch (commandWord) {
        case COMMAND_LIST:
            Ui.showTaskList(tasks, taskCount);
            break;
        case COMMAND_TODO:
            taskCount = addTask(tasks, taskCount, Parser.parseTodo(command));
            break;
        case COMMAND_DEADLINE:
            taskCount = addTask(tasks, taskCount,
                    Parser.parseDeadline(command));
            break;
        case COMMAND_EVENT:
            taskCount = addTask(tasks, taskCount, Parser.parseEvent(command));
            break;
        case COMMAND_MARK:
            markTask(command, tasks);
            break;
        case COMMAND_UNMARK:
            unmarkTask(command, tasks);
            break;
        default:
            taskCount = addUnrecognizedCommandAsTask(command, tasks,
                    taskCount);
            break;
        }

        return taskCount;
    }

    private static int addTask(Task[] tasks, int taskCount, Task task) {
        tasks[taskCount] = task;
        int updatedTaskCount = taskCount + 1;

        Ui.showTaskAdded(task, updatedTaskCount);
        return updatedTaskCount;
    }

    private static void markTask(String command, Task[] tasks) {
        int taskNumber = parseTaskNumber(command, COMMAND_MARK);
        Task task = tasks[taskNumber - 1];

        task.markAsDone();
        Ui.showTaskMarked(task);
    }

    private static void unmarkTask(String command, Task[] tasks) {
        int taskNumber = parseTaskNumber(command, COMMAND_UNMARK);
        Task task = tasks[taskNumber - 1];

        task.markAsNotDone();
        Ui.showTaskUnmarked(task);
    }

    private static int parseTaskNumber(String command, String commandWord) {
        String taskNumber = command.substring(
                commandWord.length() + COMMAND_SEPARATOR.length());

        return Integer.parseInt(taskNumber);
    }

    private static int addUnrecognizedCommandAsTask(String command,
            Task[] tasks, int taskCount) {
        tasks[taskCount] = new Task(command);
        System.out.println("added: " + command);

        return taskCount + 1;
    }
}
