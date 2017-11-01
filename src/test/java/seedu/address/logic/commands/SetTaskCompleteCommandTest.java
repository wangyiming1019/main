package seedu.address.logic.commands;
//@@author Esilocke
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalTasksOnlyAddressBook;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.ReadOnlyTask;

public class SetTaskCompleteCommandTest {
    private Model model = new ModelManager(getTypicalTasksOnlyAddressBook(), new UserPrefs());

    @Test
    public void execute_validTaskIndex_success() throws Exception {
        ReadOnlyTask taskToChange = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        SetTaskCompleteCommand setCompleteCommand = prepareCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(setCompleteCommand.MESSAGE_SUCCESS, taskToChange);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setAsComplete(taskToChange, true);

        assertCommandSuccess(setCompleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidTaskIndex_failure() throws Exception {
        Index outOfRangeIndex = Index.fromZeroBased(model.getFilteredTaskList().size());
        SetTaskCompleteCommand setCompleteCommand = prepareCommand(outOfRangeIndex);

        assertCommandFailure(setCompleteCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_changeCompletedTask_failure() throws Exception {
        ReadOnlyTask taskToChange = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setAsComplete(taskToChange, true);
        SetTaskCompleteCommand setCompleteCommand = new SetTaskCompleteCommand(INDEX_FIRST_TASK);
        setCompleteCommand.setData(expectedModel, new CommandHistory(), new UndoRedoStack());

        assertCommandFailure(setCompleteCommand, expectedModel, setCompleteCommand.MESSAGE_TASK_ALREADY_COMPLETE);
    }

    @Test
    public void equals() {
        SetTaskCompleteCommand setFirstCommand = new SetTaskCompleteCommand(INDEX_FIRST_TASK);
        SetTaskCompleteCommand setSecondCommand = new SetTaskCompleteCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(setFirstCommand.equals(setFirstCommand));

        // same values -> returns true
        SetTaskCompleteCommand setFirstCommandCopy = new SetTaskCompleteCommand(INDEX_FIRST_TASK);
        assertTrue(setFirstCommand.equals(setFirstCommandCopy));

        // different types -> returns false
        assertFalse(setFirstCommand.equals(1));

        // null -> returns false
        assertFalse(setFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(setFirstCommand.equals(setSecondCommand));
    }

    /**
     * Returns a {@code SetTaskCompleteCommand} with the parameter {@code index}.
     */
    public SetTaskCompleteCommand prepareCommand(Index taskIndex) {
        SetTaskCompleteCommand command = new SetTaskCompleteCommand(taskIndex);
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        return command;
    }
}
