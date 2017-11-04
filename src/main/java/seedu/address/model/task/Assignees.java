package seedu.address.model.task;

import java.util.ArrayList;

import seedu.address.commons.core.index.Index;
import seedu.address.model.person.ReadOnlyPerson;
/**
 * Represents the list of {@code ReadOnlyPerson} assigned to a task.
 * Contains support for some limited modification operations
 */
public class Assignees {
    private ArrayList<Index> assignedList;

    public Assignees(ArrayList<Index> assignees) {
        this.assignedList = assignees;
    }

    public Assignees() {
        this.assignedList = new ArrayList<>();
    }

    /** Assigns all {@code ReadOnlyPerson} in the specified list */
    public void assign(ArrayList<Index> personsToAssign) {
        for (Index i : personsToAssign) {
            if (!assignedList.contains(i)) {
                assignedList.add(i);
            }
        }
    }

    /** Removes all {@code ReadOnlyPerson} from the specified list */
    public void dismiss(ArrayList<Index> personsToDismiss) {
        assignedList.removeAll(personsToDismiss);
    }

    public boolean contains(ReadOnlyPerson toFind) {
        return assignedList.contains(toFind);
    }

    public ArrayList<Index> getList() {
        return this.assignedList;
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
