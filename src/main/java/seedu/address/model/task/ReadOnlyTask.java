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
}
