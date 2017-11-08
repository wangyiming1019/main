package seedu.address.logic.commands;

/**
 * Lists all persons in the address book to the user.
 */
public class ChangePasswordCommand extends Command {

    public static final String COMMAND_WORD = "changepassword";
    public static final String COMMAND_ALIAS = "cps";

    public static final String MESSAGE_SUCCESS = "Password changed successfully";


    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
