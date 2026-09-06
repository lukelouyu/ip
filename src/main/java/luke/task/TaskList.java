package luke.task;

import java.util.Arrays;

import luke.exception.LukeException;

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
     * @throws LukeException If the task number does not refer to an existing task.
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
     * @throws LukeException If the task number does not refer to an existing task.
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
                    "That task number does not exist. Please choose the number between 1 and "
                            + taskCount + ".");
        }
    }

    /**
     * Returns a copy of all stored tasks.
     *
     * @return Array containing the tasks.
     */
    public Task[] getTasks() {
        return Arrays.copyOf(tasks, taskCount);
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
