package seedu.address.model.task;

/**
 * Provides an immutable interface for a Task in the address book.
 */
public interface ReadOnlyTask {

    Name getName();
    Description getDescription();
    Deadline getDeadline();
    Priority getPriority();
}
