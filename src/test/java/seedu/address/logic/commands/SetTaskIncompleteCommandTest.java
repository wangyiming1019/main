package seedu.address.logic.commands;
//@@author Esilocke
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TASK;
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

public class SetTaskIncompleteCommandTest {
    private Model model = new ModelManager(getTypicalTasksOnlyAddressBook(), new UserPrefs());

    @Test
    public void execute_validTaskIndex_success() throws Exception {
        ReadOnlyTask taskToChange = model.getFilteredTaskList().get(INDEX_THIRD_TASK.getZeroBased());
        SetTaskIncompleteCommand setIncompleteCommand = prepareCommand(INDEX_THIRD_TASK);

        String expectedMessage = String.format(setIncompleteCommand.MESSAGE_SUCCESS, taskToChange);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setAsComplete(taskToChange, false);

        assertCommandSuccess(setIncompleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidTaskIndex_failure() throws Exception {
        Index outOfRangeIndex = Index.fromZeroBased(model.getFilteredTaskList().size());
        SetTaskIncompleteCommand setIncompleteCommand = prepareCommand(outOfRangeIndex);

        assertCommandFailure(setIncompleteCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_changeCompletedTask_failure() throws Exception {
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        SetTaskIncompleteCommand setIncompleteCommand = new SetTaskIncompleteCommand(INDEX_FIRST_TASK);
        setIncompleteCommand.setData(expectedModel, new CommandHistory(), new UndoRedoStack());

        assertCommandFailure(setIncompleteCommand, expectedModel, setIncompleteCommand.MESSAGE_TASK_ALREADY_COMPLETE);
    }

    @Test
    public void equals() {
        SetTaskIncompleteCommand setFirstCommand = new SetTaskIncompleteCommand(INDEX_FIRST_TASK);
        SetTaskIncompleteCommand setSecondCommand = new SetTaskIncompleteCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(setFirstCommand.equals(setFirstCommand));

        // same values -> returns true
        SetTaskIncompleteCommand setFirstCommandCopy = new SetTaskIncompleteCommand(INDEX_FIRST_TASK);
        assertTrue(setFirstCommand.equals(setFirstCommandCopy));

        // different types -> returns false
        assertFalse(setFirstCommand.equals(1));

        // null -> returns false
        assertFalse(setFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(setFirstCommand.equals(setSecondCommand));
    }

    /**
     * Returns a {@code SetTaskIncompleteCommand} with the parameter {@code index}.
     */
    public SetTaskIncompleteCommand prepareCommand(Index taskIndex) {
        SetTaskIncompleteCommand command = new SetTaskIncompleteCommand(taskIndex);
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        return command;
    }
}
