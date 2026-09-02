package luke;

public class Deadline extends Task {
    private final String by;

    /**
     * Creates an incomplete deadline.
     *
     * @param description Description of the deadline.
     * @param by Due date or time of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}