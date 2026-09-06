package luke;

/**
 * Parses user commands into task objects and command parameters.
 */
public class Parser {
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";

    private static final String COMMAND_SEPARATOR = " ";
    private static final String DEADLINE_SEPARATOR = " /by ";
    private static final String EVENT_FROM_SEPARATOR = " /from ";
    private static final String EVENT_TO_SEPARATOR = " /to ";

    private static final int SPLIT_LIMIT = 2;

    /**
     * Parses a todo command.
     *
     * @param command User command.
     * @return Parsed todo.
     * @throws LukeException If the todo description is empty.
     */
    public static Todo parseTodo(String command) throws LukeException {
        String description = command.substring(COMMAND_TODO.length()).trim();

        if (description.isEmpty()) {
            throw new LukeException(
                    "[WARNING] The description of a todo cannot be empty.");
        }

        return new Todo(description);
    }

    /**
     * Parses a deadline command.
     *
     * @param command User command.
     * @return Parsed deadline.
     * @throws LukeException If the description or deadline is missing.
     */
    public static Deadline parseDeadline(String command) throws LukeException {
        String commandDetails = command.substring(
                COMMAND_DEADLINE.length()).trim();

        if (commandDetails.isEmpty()) {
            throw new LukeException(
                    "[WARNING] The description of a deadline cannot be empty.");
        }

        String[] deadlineParts = commandDetails.split(
                DEADLINE_SEPARATOR, SPLIT_LIMIT);

        if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty()) {
            throw new LukeException(
                    "[WARNING] The deadline must include a /by date or time.");
        }

        String description = deadlineParts[0].trim();
        String by = deadlineParts[1].trim();

        if (description.isEmpty()) {
            throw new LukeException(
                    "[WARNING] The description of a deadline cannot be empty.");
        }

        return new Deadline(description, by);
    }

    /**
     * Parses an event command.
     *
     * @param command User command.
     * @return Parsed event.
     * @throws LukeException If the description, start time, or end time is missing.
     */
    public static Event parseEvent(String command) throws LukeException {
        String commandDetails = command.substring(
                COMMAND_EVENT.length()).trim();

        if (commandDetails.isEmpty()) {
            throw new LukeException(
                    "[WARNING] The description of an event cannot be empty.");
        }

        String[] eventParts = commandDetails.split(
                EVENT_FROM_SEPARATOR, SPLIT_LIMIT);

        if (eventParts.length < 2) {
            throw new LukeException(
                    "[WARNING] The event must include a /from start time.");
        }

        String description = eventParts[0].trim();
        String eventTimeDetails = eventParts[1].trim();

        if (description.isEmpty()) {
            throw new LukeException(
                    "[WARNING] The description of an event cannot be empty.");
        }

        String[] timeParts = eventTimeDetails.split(
                EVENT_TO_SEPARATOR, SPLIT_LIMIT);

        if (timeParts.length < 2) {
            throw new LukeException(
                    "[WARNING] The event must include a /to end time.");
        }

        String from = timeParts[0].trim();
        String to = timeParts[1].trim();

        if (from.isEmpty()) {
            throw new LukeException(
                    "[WARNING] The event start time cannot be empty.");
        }

        if (to.isEmpty()) {
            throw new LukeException(
                    "[WARNING] The event end time cannot be empty.");
        }

        return new Event(description, from, to);
    }

    /**
     * Extracts a task number from a command.
     *
     * @param command User command.
     * @param commandWord Command word, such as {@code mark} or {@code unmark}.
     * @return Parsed task number.
     * @throws LukeException If the task number is missing or is not a valid integer.
     */
    public static int parseTaskNumber(String command, String commandWord)
            throws LukeException {
        String taskNumberText = command.substring(
                commandWord.length()).trim();

        if (taskNumberText.isEmpty()) {
            throw new LukeException(
                    "[WARNING] Please specify a task number.");
        }

        try {
            return Integer.parseInt(taskNumberText);
        } catch (NumberFormatException e) {
            throw new LukeException(
                    "[WARNING] The task number must be a valid number.");
        }
    }

    /**
     * Extracts the command word from a user command.
     *
     * @param command User command.
     * @return Command word.
     */
    public static String parseCommandWord(String command) {
        return command.split(COMMAND_SEPARATOR, SPLIT_LIMIT)[0];
    }
}