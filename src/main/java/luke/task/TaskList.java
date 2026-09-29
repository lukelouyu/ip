package luke.task;

import java.util.ArrayList;

import luke.exception.LukeException;

/**
 * Stores and manages the user's tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;


    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int taskNumber) throws LukeException {
        validateTaskNumber(taskNumber);

        return tasks.remove(taskNumber - 1);
    }

    public Task mark(int taskNumber) throws LukeException {
        validateTaskNumber(taskNumber);

        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        return task;
    }

    public Task unmark(int taskNumber) throws LukeException {
        validateTaskNumber(taskNumber);

        Task task = tasks.get(taskNumber - 1);
        task.markAsNotDone();
        return task;
    }

    /**
     * Returns tasks whose descriptions contain the specified keyword.
     * Matching is case-insensitive and preserves task order.
     *
     * @param keyword Keyword to find in task descriptions.
     * @return Matching tasks in their original order.
     */
    public Task[] find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.containsKeyword(keyword)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks.toArray(new Task[0]);
    }

    private void validateTaskNumber(int taskNumber) throws LukeException {
        if (tasks.isEmpty()) {
            throw new LukeException("There are no tasks in the list.");
        }

        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new LukeException(
                    "That task number does not exist. Please choose a number between 1 and "
                            + tasks.size() + ".");
        }
    }

    public Task[] getTasks() {
        return tasks.toArray(new Task[0]);
    }

    public int getTaskCount() {
        return tasks.size();
    }

}
