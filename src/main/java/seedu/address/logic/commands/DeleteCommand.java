package seedu.address.logic.commands;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Deletes a person identified using it's last displayed index from the address book.
 */
public class DeleteCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_ALIAS = "d";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the last person listing.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Task: %1$s";
    public static final int DELETE_TYPE_PERSON = 0;
    public static final int DELETE_TYPE_TASK = 1;

    private final Index targetIndex;
    private int type;

    public DeleteCommand(Index targetIndex, int objectType) {
        this.targetIndex = targetIndex;
        this.type = objectType;
    }


    @Override
    public CommandResult executeUndoableCommand() throws CommandException {

        List<ReadOnlyPerson> personsList = model.getFilteredPersonList();
        List<ReadOnlyTask> tasksList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= personsList.size() && type == DELETE_TYPE_PERSON) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } else if (targetIndex.getZeroBased() >= tasksList.size() && type == DELETE_TYPE_TASK) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        try {
            if (type == DELETE_TYPE_PERSON) {
                ReadOnlyPerson personToDelete = personsList.get(targetIndex.getZeroBased());
                model.deletePerson(personToDelete);
                return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
            } else {
                ReadOnlyTask taskToDelete = personsList.get(targetIndex.getZeroBased());
                model.deleteTask(taskToDelete);
                return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete));
            }

        } catch (PersonNotFoundException pnfe) {
            assert false : "The target person cannot be missing";
        } catch (TaskNotFoundException tnfe) {
            assert false : "The target task cannot be missing";
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && this.targetIndex.equals(((DeleteCommand) other).targetIndex)
                && this.type == ((DeleteCommand) other).type); // state check
    }
}
