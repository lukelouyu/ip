package luke;

/**
 * Represent errors caused by invalid user command
 */
public class LukeException extends Exception{

    /**
     * Creates a Luke-specific exception with given message
     *
     * @param message Explanation of the error
     */
    public LukeException(String message){
        super(message);
    }
}
