package seedu.address.logic.commands;

//@@author Esilocke

import static seedu.address.logic.parser.CliSyntax.PREFIX_TARGET;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.task.ReadOnlyTask;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;

/** Dismisses at least 1 person from a specified task in the Address Book**/
public class DismissTaskCommand extends Command {
    public static final String COMMAND_WORD = "dismiss";
    public static final String COMMAND_ALIAS = "ds";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Dismisses people from a task in the Address Book. "
            + "Parameters: "
            + "PERSON INDEXES... "
            + PREFIX_TARGET + "TASK ";

    public static final String MESSAGE_SUCCESS = "Dismissed %1$s people from task \n%2$s";
    public static final String MESSAGE_INVALID_TARGET_ARGS = "Only 1 task index should be specified";
    public static final String MESSAGE_INVALID_PERSONS_ARGS = "At least 1 person index must be specified";
    public static final String MESSAGE_NONE_ASSIGNED = "None of the specified persons are assigned to this task";

    private ArrayList<Index> personIndexes;
    private Index taskIndex;

    public DismissTaskCommand(ArrayList<Index> personIndexes, Index taskIndex) {
        assert(personIndexes.size() > 0);
        this.personIndexes = personIndexes;
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResult execute() throws CommandException {
        List<ReadOnlyTask> tasksList = model.getFilteredTaskList();
        ArrayList<ReadOnlyPerson> personIndexes = createPersonsToDismiss(this.personIndexes);

        if (taskIndex.getZeroBased() >= tasksList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        ReadOnlyTask dismissedTask = tasksList.get(taskIndex.getZeroBased());
        try {
            model.dismissFromTask(personIndexes, dismissedTask);
        } catch (TaskNotFoundException tnfe) {
            throw new AssertionError("The specified task cannot be missing");
        } catch (DuplicateTaskException dte) {
            throw new CommandException(MESSAGE_NONE_ASSIGNED);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, personIndexes.size(), dismissedTask));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DismissTaskCommand // instanceof handles nulls
                && this.personIndexes.equals(((DismissTaskCommand) other).personIndexes)
                && this.taskIndex.equals(((DismissTaskCommand) other).taskIndex)); // state check
    }

    /**
     * Creates a {@code ArrayList} that contains all the {@code ReadOnlyPerson} converted from the {@Code Index}
     * @throws CommandException if the specified Index is out of range
     */
    public ArrayList<ReadOnlyPerson> createPersonsToDismiss (ArrayList<Index> indexes)  throws CommandException {
        HashSet<ReadOnlyPerson> addedPersons = new HashSet<>();
        ArrayList<ReadOnlyPerson> personsToDismiss = new ArrayList<>();
        List<ReadOnlyPerson> personsList = model.getFilteredPersonList();
        try {
            for (Index i : personIndexes) {
                ReadOnlyPerson toDismiss = personsList.get(i.getZeroBased());
                if (!addedPersons.contains(toDismiss)) {
                    addedPersons.add(toDismiss);
                    personsToDismiss.add(toDismiss);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        return personsToDismiss;
    }
}
