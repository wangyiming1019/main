package seedu.address.logic;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.task.ReadOnlyTask;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager extends ComponentManager implements Logic {
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final CommandHistory history;
    private final AddressBookParser addressBookParser;
    private final UndoRedoStack undoRedoStack;

    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        this.history = new CommandHistory();
        this.addressBookParser = new AddressBookParser();
        this.undoRedoStack = new UndoRedoStack();
    }

    public LogicManager(Model model) {
        this.model = model;
        this.storage = null;
        this.history = new CommandHistory();
        this.addressBookParser = new AddressBookParser();
        this.undoRedoStack = new UndoRedoStack();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        try {
            Command command = addressBookParser.parseCommand(commandText, this.getLockState());
            command.setData(model, history, undoRedoStack);
            command.setStorageInfo(this.storage);
            CommandResult result = command.execute();
            undoRedoStack.push(command);
            return result;
        } finally {
            history.add(commandText);
        }
    }
    //@@author charlesgoh
    @Override
    public boolean getLockState() {
        UserPrefs userPrefs;
        if (storage == null) {
            logger.warning("Storage is null");
            return false;
        }
        try {
            userPrefs = storage.readUserPrefs().get();
        } catch (DataConversionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // For unexpected cases, return false
        return false;
    }
    //@@author
    @Override
    public ObservableList<ReadOnlyPerson> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    //@@author Esilocke
    @Override
    public ObservableList<ReadOnlyTask> getFilteredTaskList() {
        return model.getFilteredTaskList();
    }
    //@@author

    @Override
    public ListElementPointer getHistorySnapshot() {
        return new ListElementPointer(history.getHistory());
    }
}
