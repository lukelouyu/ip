package luke.storage;

import java.io.FileWriter;
import java.io.IOException;

import luke.task.Task;

/**
 * Handles saving and loading tasks from the data file.
 */
public class Storage {
    private final String filePath;

    /**
     * Creates a storage object using the specified file path.
     *
     * @param filePath Path of the data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the tasks to the data file.
     *
     * @param tasks Tasks to save.
     * @param taskCount Number of tasks to save.
     * @throws IOException If the data file cannot be written.
     */
    public void save(Task[] tasks, int taskCount) throws IOException {
        FileWriter writer = new FileWriter(filePath);

        for (int i = 0; i < taskCount; i++) {
            writer.write(tasks[i].toDataString());
            writer.write(System.lineSeparator());
        }

        writer.close();
    }
}