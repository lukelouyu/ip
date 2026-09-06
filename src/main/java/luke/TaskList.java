package luke;

/**
 * Stores and manages the user's tasks.
 */
public class TaskList {
    private static final int MAX_TASKS = 100;

    private final Task[] tasks;
    private int taskCount;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        tasks = new Task[MAX_TASKS];
        taskCount = 0;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to add.
     */
    public void add(Task task) {
        tasks[taskCount] = task;
        taskCount++;
    }

    /**
     * Marks the specified task as done.
     *
     * @param taskNumber One-based task number.
     * @return Task that was marked.
     */
    public Task mark(int taskNumber) throws LukeException {
        validateTaskNumber(taskNumber);

        Task task = tasks[taskNumber - 1];
        task.markAsDone();
        return task;
    }

    /**
     * Marks the specified task as not done.
     *
     * @param taskNumber One-based task number.
     * @return Task that was unmarked.
     */
    public Task unmark(int taskNumber) throws LukeException {
        validateTaskNumber(taskNumber);

        Task task = tasks[taskNumber - 1];
        task.markAsNotDone();
        return task;
    }

    private void validateTaskNumber(int taskNumber) throws LukeException {
        if (taskNumber < 1 || taskNumber > taskCount) {
            throw new LukeException(
                    "That task number does not exist.");
        }
    }

    /**
     * Returns all stored tasks.
     *
     * @return Array containing the tasks.
     */
    public Task[] getTasks() {
        return tasks;
    }

    /**
     * Returns the number of stored tasks.
     *
     * @return Number of tasks.
     */
    public int getTaskCount() {
        return taskCount;
    }
}
