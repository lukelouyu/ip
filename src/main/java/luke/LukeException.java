package luke;

/**
 * Represents errors caused by invalid user commands.
 */
public class LukeException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a Luke-specific exception with the given message.
     *
     * @param message Explanation of the error.
     */
    public LukeException(String message) {
        super(message);
    }
}
