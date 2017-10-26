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

    private static final String TYPE_PERSONS = "Contact list";
    private static final String TYPE_TASKS = "Task list";
    private static final String TYPE_ALL = "Address book";

    private boolean isClearTask;
    private boolean isClearPerson;
    private boolean isClearAll;
    private String typeCleared;

    public ClearCommand() {
        isClearAll = true;
        isClearPerson = false;
        isClearTask = false;
        typeCleared = TYPE_ALL;
    }
    public ClearCommand(Prefix type) {
        if (type.getPrefix().equals(PREFIX_TASK)) {
            isClearTask = true;
            isClearPerson = false;
            isClearAll = false;
            typeCleared = TYPE_TASKS;
        } else if (type.getPrefix().equals(PREFIX_PERSON)) {
            isClearPerson = true;
            isClearTask = false;
            isClearAll = false;
            typeCleared = TYPE_PERSONS;
        } else {
            throw new AssertionError("An invalid type was provided!");
        }
    }

    @Override
    public CommandResult executeUndoableCommand() {
        requireNonNull(model);
        if (isClearAll) {
            model.resetData(new AddressBook());
        } else if (isClearTask) {
            model.resetTasks();
        } else if (isClearPerson) {
            model.resetContacts();
        } else {
            assert false : "At least one boolean must be true.";
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, typeCleared));
    }
}
