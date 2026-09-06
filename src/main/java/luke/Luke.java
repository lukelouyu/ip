package luke;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

import luke.exception.LukeException;
import luke.parser.Parser;
import luke.storage.Storage;
import luke.task.Task;
import luke.task.TaskList;
import luke.ui.Ui;

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

    /**
     * Starts Luke and processes commands until the user exits.
     *
     * @param args Command-line arguments; not used.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        TaskList tasks = new TaskList();
        Storage storage = new Storage(
                Path.of("data", "luke.txt").toString());

        Ui.showWelcome();

        String command = input.nextLine();

        while (!command.equals("bye")) {
            Ui.showHorizontalLine();

            try {
                processCommand(command, tasks);
            } catch (LukeException e) {
                Ui.showError(e.getMessage());
            }

            Ui.showHorizontalLine();
            command = input.nextLine();
        }

        Ui.showGoodbye();
    }

    private static void processCommand(String command, TaskList tasks)
            throws LukeException {
        String commandWord = Parser.parseCommandWord(command);

        switch (commandWord) {
        case COMMAND_LIST:
            Ui.showTaskList(tasks.getTasks(), tasks.getTaskCount());
            break;

        case COMMAND_TODO:
            Task todo = Parser.parseTodo(command);
            tasks.add(todo);
            Ui.showTaskAdded(todo, tasks.getTaskCount());
            break;

        case COMMAND_DEADLINE:
            Task deadline = Parser.parseDeadline(command);
            tasks.add(deadline);
            Ui.showTaskAdded(deadline, tasks.getTaskCount());
            break;

        case COMMAND_EVENT:
            Task event = Parser.parseEvent(command);
            tasks.add(event);
            Ui.showTaskAdded(event, tasks.getTaskCount());
            break;

        case COMMAND_MARK:
            int markNumber = Parser.parseTaskNumber(command, COMMAND_MARK);
            Task markedTask = tasks.mark(markNumber);
            Ui.showTaskMarked(markedTask);
            break;

        case COMMAND_UNMARK:
            int unmarkNumber = Parser.parseTaskNumber(command, COMMAND_UNMARK);
            Task unmarkedTask = tasks.unmark(unmarkNumber);
            Ui.showTaskUnmarked(unmarkedTask);
            break;

        default:
            throw new LukeException(
                    "Sorry, your command is unrecognized.");
        }
    }

}
