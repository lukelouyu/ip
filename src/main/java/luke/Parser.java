package luke;

/**
 * Parses user commands into task objects.
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
     */
    public static Todo parseTodo(String command) throws LukeException{
        String description = command.substring(
                COMMAND_TODO.length() + COMMAND_SEPARATOR.length());

        if (description.isEmpty()) {
            throw new LukeException("The description of a todo cannot be empty.");
        }

        return new Todo(description);
    }

    public static Deadline parseDeadline(String command) throws LukeException {
        String commandDetails = command.substring(COMMAND_DEADLINE.length()).trim();

        if (commandDetails.isEmpty()) {
            throw new LukeException(
                    "The description of a deadline cannot be empty.");
        }

        String[] deadlineParts = commandDetails.split(
                DEADLINE_SEPARATOR, SPLIT_LIMIT);

        if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty()) {
            throw new LukeException(
                    "The deadline must include a /by date or time.");
        }

        String description = deadlineParts[0].trim();
        String by = deadlineParts[1].trim();

        if (description.isEmpty()) {
            throw new LukeException(
                    "The description of a deadline cannot be empty.");
        }

        return new Deadline(description, by);
    }

    /**
     * Parses an event command.
     *
     * @param command User command.
     * @return Parsed event.
     */
    public static Event parseEvent(String command) {
        String commandDetails = command.substring(
                COMMAND_EVENT.length() + COMMAND_SEPARATOR.length());

        String[] eventParts = commandDetails.split(
                EVENT_FROM_SEPARATOR, SPLIT_LIMIT);

        String description = eventParts[0];

        String[] timeParts = eventParts[1].split(
                EVENT_TO_SEPARATOR, SPLIT_LIMIT);

        String from = timeParts[0];
        String to = timeParts[1];

        return new Event(description, from, to);
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

    /**
     * Extracts the task number from a mark or unmark command.
     *
     * @param command     User command.
     * @param commandWord Command word.
     * @return Task number.
     */
    public static int parseTaskNumber(String command, String commandWord) {
        String taskNumber = command.substring(
                commandWord.length() + COMMAND_SEPARATOR.length());

        return Integer.parseInt(taskNumber);
    }
}
