package seedu.address.model.task;

import java.util.ArrayList;
import java.util.HashSet;

import seedu.address.model.person.ReadOnlyPerson;
/**
 * Represents the list of {@code ReadOnlyPerson} assigned to a task.
 * Contains support for some limited modification operations
 */
public class Assignees {
    private HashSet<ReadOnlyPerson> assignedList;

    public Assignees(HashSet<ReadOnlyPerson> assignees) {
        this.assignedList = assignees;
    }

    public Assignees() {
        this.assignedList = new HashSet<>();
    }

    /** Assigns all {@code ReadOnlyPerson} in the specified list */
    public void assign(ArrayList<ReadOnlyPerson> personsToAssign) {
        for (ReadOnlyPerson p : personsToAssign) {
            assignedList.add(p);
        }
    }

    /** Removes all {@code ReadOnlyPerson} from the specified list */
    public void dismiss(ArrayList<ReadOnlyPerson> personsToDismiss) {
        for (ReadOnlyPerson p : personsToDismiss) {
            if (assignedList.contains(p)) {
                assignedList.remove(p);
            }
        }
    }

    public void replace(ReadOnlyPerson target, ReadOnlyPerson replacement) {
    }

    public boolean contains(ReadOnlyPerson toFind) {
        return assignedList.contains(toFind);
    }

    @Override
    public String toString() {
        return assignedList.size() + " persons assigned";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Assignees // instanceof handles nulls
                && this.assignedList.equals(((Assignees) other).assignedList)); // state check
    }
}
