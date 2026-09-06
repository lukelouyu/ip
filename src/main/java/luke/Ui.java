package luke;

/**
 * Handles the display of messages to the user.
 */
public class Ui {
    private static final int HORIZONTAL_LINE_LENGTH = 60;
    private static final String HORIZONTAL_LINE = createHorizontalLine();

    private static String getLogo() {
        return " _          _        \n"
                + "| |   _   _| | _____ \n"
                + "| |  | | | | |/ / _ \\\n"
                + "| |__| |_| |   <  __/\n"
                + "|_____\\__,_|_|\\_\\___|\n";
    }

    /**
     * Shows the welcome message and logo.
     */
    public static void showWelcome() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Luke\n" + getLogo());
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Shows the goodbye message.
     */
    public static void showGoodbye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Shows all tasks currently stored in the task list.
     *
     * @param tasks     Tasks to display.
     * @param taskCount Number of stored tasks.
     */
    public static void showTaskList(Task[] tasks, int taskCount) {
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
    }

    /**
     * Shows confirmation that a task was added.
     *
     * @param task      Added task.
     * @param taskCount Updated number of stored tasks.
     */
    public static void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Shows confirmation that a task was marked as done.
     *
     * @param task Marked task.
     */
    public static void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Shows confirmation that a task was marked as not done.
     *
     * @param task Unmarked task.
     */
    public static void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    /**
     * Shows a horizontal line separating output sections.
     */
    public static void showHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    private static String createHorizontalLine() {
        StringBuilder horizontalLine = new StringBuilder();

        for (int i = 0; i < HORIZONTAL_LINE_LENGTH; i++) {
            horizontalLine.append("_");
        }

        return horizontalLine.toString();
    }

    /**
     * Shows a message for an unrecognized command.
     */
    public static void showUnrecognizedCommand() {
        System.out.println("Sorry, your command is unrecognized.");
    }

    /**
     * Shows an error message.
     *
     * @param message Error message to display.
     */
    public static void showError(String message) {
        System.out.println(message);
    }

}
