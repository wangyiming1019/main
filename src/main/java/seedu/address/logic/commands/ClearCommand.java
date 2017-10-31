package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.AddressBook;

/**
 * Clears the address book.
 */
public class ClearCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "clear";
    public static final String COMMAND_ALIAS = "c";
    public static final String MESSAGE_SUCCESS = "%s has been cleared!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears the address book. "
            + "Parameters: "
            + "[" + PREFIX_PERSON + "] "
            + "[" + PREFIX_TASK + "] ";

    public static final String TYPE_PERSONS = "Contact list";
    public static final String TYPE_TASKS = "Task list";
    public static final String TYPE_ALL = "Address book";

    private boolean isClearTask;
    private boolean isClearPerson;
    private boolean isClearAll;
    private Prefix type;
    private String cleared;
    //@@author Esilocke
    public ClearCommand() {
        isClearAll = true;
        isClearPerson = false;
        isClearTask = false;
        type = null;
        cleared = TYPE_ALL;
    }
    //@@author Esilocke
    public ClearCommand(Prefix type) {
        if (type.equals(PREFIX_TASK)) {
            isClearTask = true;
            isClearPerson = false;
            isClearAll = false;
            this.type = PREFIX_TASK;
            cleared = TYPE_TASKS;
        } else if (type.equals(PREFIX_PERSON)) {
            isClearPerson = true;
            isClearTask = false;
            isClearAll = false;
            this.type = PREFIX_PERSON;
            cleared = TYPE_PERSONS;
        } else {
            throw new AssertionError("An invalid type was provided!");
        }
    }

    @Override
    //@@author Esilocke
    public CommandResult executeUndoableCommand() {
        requireNonNull(model);
        if (isClearAll) {
            model.resetData(new AddressBook());
        } else if (isClearTask) {
            model.resetPartialData(new AddressBook(), type);
        } else if (isClearPerson) {
            model.resetPartialData(new AddressBook(), type);
        } else {
            assert false : "At least one boolean must be true.";
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, cleared));
    }
}
