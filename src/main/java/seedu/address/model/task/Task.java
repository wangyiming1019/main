package seedu.address.model.task;

import java.util.Objects;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

//@@author Esilocke
/**
 * Represents a task object in the address book.
 */
public class Task implements ReadOnlyTask {

    private ObjectProperty<TaskName> taskName;
    private ObjectProperty<Description> description;
    private ObjectProperty<Deadline> deadline;
    private ObjectProperty<Priority> priority;
    private ObjectProperty<Assignees> assignees;

    public Task(TaskName taskName, Description description, Deadline deadline, Priority priority,
                Assignees assignees) {
        this.taskName = new SimpleObjectProperty<>(taskName);
        this.description = new SimpleObjectProperty<>(description);
        this.deadline = new SimpleObjectProperty<>(deadline);
        this.priority = new SimpleObjectProperty<>(priority);
        this.assignees = new SimpleObjectProperty<>(assignees);
    }

    public Task(TaskName taskName, Description description, Deadline deadline, Priority priority) {
        this.taskName = new SimpleObjectProperty<>(taskName);
        this.description = new SimpleObjectProperty<>(description);
        this.deadline = new SimpleObjectProperty<>(deadline);
        this.priority = new SimpleObjectProperty<>(priority);
        this.assignees = new SimpleObjectProperty<>(new Assignees());
    }

    public Task(ReadOnlyTask task) {
        this(task.getTaskName(), task.getDescription(), task.getDeadline(), task.getPriority(),
                task.getAssignees());
    }

    public TaskName getTaskName() {
        return taskName.get();
    }

    @Override
    public Description getDescription() {
        return description.get();
    }

    @Override
    public Deadline getDeadline() {
        return deadline.get();
    }

    @Override
    public Priority getPriority() {
        return priority.get();
    }

    @Override
    public Assignees getAssignees() {
        return assignees.get();
    }

    @Override
    public String toString() {
        return getAsText();
    }

    // JavaFX property functions
    @Override
    public ObjectProperty<TaskName> taskNameProperty() {
        return taskName;
    }

    @Override
    public ObjectProperty<Description> descriptionProperty() {
        return description;
    }

    @Override
    public ObjectProperty<Deadline> deadlineProperty() {
        return deadline;
    }

    @Override
    public ObjectProperty<Priority> priorityProperty() {
        return priority;
    }

    @Override
    public ObjectProperty<Assignees> assigneeProperty() {
        return assignees;
    }
    // Setters for TaskBuilder testing

    public void setTaskName(TaskName taskName) {
        this.taskName.set(taskName);
    }

    public void setDeadline(Deadline deadline) {
        this.deadline.set(deadline);
    }

    public void setDescription(Description description) {
        this.description.set(description);
    }

    public void setPriority(Priority priority) {
        this.priority.set(priority);
    }

    public void setAssignees(Assignees assignees) {
        this.assignees.set(assignees);
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ReadOnlyTask // instanceof handles nulls
            && this.isSameStateAs((ReadOnlyTask) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(taskName, description, deadline, priority, assignees);
    }
}
