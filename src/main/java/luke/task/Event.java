package luke.task;

/**
 * Represents a task scheduled between a start and end time.
 */
public class Event extends Task {
    private final String from;
    private final String to;

    /**
     * Creates an incomplete event.
     *
     * @param description Description of the event.
     * @param from        Start date or time of the event.
     * @param to          End date or time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    protected String getTypeIcon() {
        return "E";
    }

    @Override
    public String toString() {
        return super.toString()
                + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toDataString() {
        String status = isDone() ? "1" : "0";

        return "E | " + status
                + " | " + getDescription()
                + " | " + from
                + " | " + to;
    }
}
