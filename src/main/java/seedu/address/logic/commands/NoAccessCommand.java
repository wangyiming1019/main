package seedu.address.logic.commands;

//@@author charlesgoh
/**
 * Prevents user from accessing any Create Read Update Delete related commands
 * when the address book state is still set to locked (i.e. lock = true)
 */
public class NoAccessCommand extends Command {

    public static final String MESSAGE_NO_ACCESS = "Not allowed! You must unlock before"
            + " making any changes.\n" + UnlockCommand.MESSAGE_USAGE;

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_NO_ACCESS);
    }
}
