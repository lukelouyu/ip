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
    public static Todo parseTodo(String command) {
        String description = command.substring(
                COMMAND_TODO.length() + COMMAND_SEPARATOR.length());

        return new Todo(description);
    }

    /**
     * Parses a deadline command.
     *
     * @param command User command.
     * @return Parsed deadline.
     */
    public static Deadline parseDeadline(String command) {
        String commandDetails = command.substring(
                COMMAND_DEADLINE.length() + COMMAND_SEPARATOR.length());

        String[] deadlineParts = commandDetails.split(
                DEADLINE_SEPARATOR, SPLIT_LIMIT);

        String description = deadlineParts[0];
        String by = deadlineParts[1];

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
}