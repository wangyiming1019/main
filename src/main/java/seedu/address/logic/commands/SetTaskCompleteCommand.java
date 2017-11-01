package seedu.address.logic.commands;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.task.ReadOnlyTask;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;

/** Marks the specified {@Code task} as complete */
public class SetTaskCompleteCommand extends Command {
    public static final String COMMAND_WORD = "setcomplete";
    public static final String COMMAND_ALIAS = "stc";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task at the specified index as <Completed>\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SELECT_PERSON_SUCCESS = "Marked Task as completed: %1$s";
    public static final String MESSAGE_TASK_ALREADY_COMPLETE = "The specified task is already completed";

    private final Index targetIndex;

    public SetTaskCompleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute() throws CommandException {

        List<ReadOnlyTask> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        try {
            ReadOnlyTask taskToComplete = lastShownList.get(targetIndex.getZeroBased());
            model.setAsComplete(taskToComplete, true);
        } catch (DuplicateTaskException dte) {
            throw new CommandException(MESSAGE_TASK_ALREADY_COMPLETE);
        } catch (TaskNotFoundException tnfe) {
            throw new AssertionError("This task cannot be missing");
        }
        return new CommandResult(String.format(MESSAGE_SELECT_PERSON_SUCCESS, targetIndex.getOneBased()));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SetTaskCompleteCommand // instanceof handles nulls
                && this.targetIndex.equals(((SetTaskCompleteCommand) other).targetIndex)); // state check
    }
}
