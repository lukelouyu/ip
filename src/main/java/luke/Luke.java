package luke;

import luke.exception.LukeException;
import luke.parser.Parser;
import luke.storage.Storage;
import luke.task.Task;
import luke.task.TaskList;
import luke.ui.Ui;

import java.io.IOException;
import java.nio.file.Path;

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
    private static final String COMMAND_DELETE = "delete";

    /**
     * Starts Luke and processes commands until the user exits.
     *
     * @param args Command-line arguments; not used.
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        Storage storage = new Storage(
                Path.of("data", "luke.txt").toString());

        try {
            storage.load(tasks);
        } catch (IOException e) {
            ui.showError("Unable to load tasks.");
        }

        ui.showWelcome();

        String command = ui.readCommand();

        while (!command.equals("bye")) {
            ui.showHorizontalLine();

            try {
                processCommand(command, tasks, storage, ui);
            } catch (LukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError("Unable to save tasks.");
            }

            ui.showHorizontalLine();
            command = ui.readCommand();
        }

        ui.showGoodbye();
    }

    private static void processCommand(String command, TaskList tasks,
                                       Storage storage, Ui ui)
            throws LukeException, IOException {
        String commandWord = Parser.parseCommandWord(command);

        switch (commandWord) {
        case COMMAND_LIST:
            ui.showTaskList(tasks.getTasks(), tasks.getTaskCount());
            break;

        case COMMAND_TODO:
            Task todo = Parser.parseTodo(command);
            tasks.add(todo);
            storage.save(tasks.getTasks(), tasks.getTaskCount());
            ui.showTaskAdded(todo, tasks.getTaskCount());
            break;

        case COMMAND_DEADLINE:
            Task deadline = Parser.parseDeadline(command);
            tasks.add(deadline);
            storage.save(tasks.getTasks(), tasks.getTaskCount());
            ui.showTaskAdded(deadline, tasks.getTaskCount());
            break;

        case COMMAND_EVENT:
            Task event = Parser.parseEvent(command);
            tasks.add(event);
            storage.save(tasks.getTasks(), tasks.getTaskCount());
            ui.showTaskAdded(event, tasks.getTaskCount());
            break;

        case COMMAND_MARK:
            int markNumber = Parser.parseTaskNumber(command, COMMAND_MARK);
            Task markedTask = tasks.mark(markNumber);
            storage.save(tasks.getTasks(), tasks.getTaskCount());
            ui.showTaskMarked(markedTask);
            break;

        case COMMAND_UNMARK:
            int unmarkNumber = Parser.parseTaskNumber(command, COMMAND_UNMARK);
            Task unmarkedTask = tasks.unmark(unmarkNumber);
            storage.save(tasks.getTasks(), tasks.getTaskCount());
            ui.showTaskUnmarked(unmarkedTask);
            break;

        case COMMAND_DELETE:
            int deleteNumber = Parser.parseTaskNumber(command, COMMAND_DELETE);
            Task deletedTask = tasks.delete(deleteNumber);
            storage.save(tasks.getTasks(), tasks.getTaskCount());
            ui.showTaskDeleted(deletedTask, tasks.getTaskCount());
            break;

        default:
            throw new LukeException(
                    "Sorry, your command is unrecognized.");
        }
    }

}
