package seedu.address.model.task;

/**
 * Provides an immutable interface for a Task in the address book.
 */
public interface ReadOnlyTask {

    TaskName getTaskName();
    Description getDescription();
    Deadline getDeadline();
    Priority getPriority();

    default String getAsText() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTaskName())
                .append(" Description: ")
                .append(getDescription())
                .append(" Deadline: ")
                .append(getDeadline())
                .append(" Priority: ")
                .append(getPriority());
        return builder.toString();
    }

    /**
     * Returns true if both have the same state. (interfaces cannot override .equals)
     */
    default boolean isSameStateAs(ReadOnlyTask other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                && other.getTaskName().equals(this.getTaskName()) // state checks here onwards
                && other.getDescription().equals(this.getDescription())
                && other.getDeadline().equals(this.getDeadline())
                && other.getPriority().equals(this.getPriority()));
    }
}
