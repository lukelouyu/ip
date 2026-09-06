package luke.task;

/**
 * Represents a task that must be completed by a specific date or time.
 */
public class Deadline extends Task {
    private final String by;

    /**
     * Creates an incomplete deadline.
     *
     * @param description Description of the deadline.
     * @param by          Due date or time of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    protected String getTypeIcon() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toDataString() {
        String status = isDone() ? "1" : "0";

        return "D | " + status + " | " + getDescription() + " | " + by;
    }
}
