package seedu.address.model.task;

/**
 * Represents a task object in the address book.
 */
public class Task implements ReadOnlyTask {

    private Name name;
    private Description description;
    private Deadline deadline;
    private Priority priority;

    public Task(Name name, Description description, Deadline deadline, Priority priority) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
    }

    public Task(ReadOnlyTask task) {
        this.name = task.getName();
        this.description = task.getDescription();
        this.deadline = task.getDeadline();
        this.priority = task.getPriority();
    }
}
