package seedu.address.logic.commands;
//@@author jeffreygohkw
import seedu.address.logic.commands.exceptions.CommandException;

/**
 *
 */
public class PrivacyLevelCommand extends UndoableCommand{

    public static final String COMMAND_WORD = "privacylevel";
    public static final String COMMAND_ALIAS = "pl";

    public static final String CHANGE_PRIVACY_LEVEL_SUCCESS = "Successfully change privacy level to %1$s.";
    public static final String WRONG_PRIVACY_LEVEL_MESSAGE = "Privacy Level can only be 0, 1 or 2";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Changes the privacy level of the address book. Level 0 shows all data, level 1 hides private fields and level 2 hides people with at least 1 private field.\n"
            + "Parameters: LEVEL (must be 0, 1 or 2)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final int level;

    public PrivacyLevelCommand(int level) {
        this.level = level;
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        if (level < 0 || level > 2) {
            throw new CommandException(WRONG_PRIVACY_LEVEL_MESSAGE);
        }
        model.setPrivacyLevel(level);
        return new CommandResult(String.format(CHANGE_PRIVACY_LEVEL_SUCCESS, Integer.toString(level)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PrivacyLevelCommand // instanceof handles nulls
                && this.level == ((PrivacyLevelCommand) other).level); // state check
    }
}
