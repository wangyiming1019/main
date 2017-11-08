package seedu.address.logic.commands;

/**
 * Lists all persons in the address book to the user.
 */
public class ChangePasswordCommand extends Command {

    public static final String COMMAND_WORD = "changepassword";
    public static final String COMMAND_ALIAS = "cps";

    public static final String MESSAGE_SUCCESS = "Password changed successfully";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Changes user password. \n"
            + "Format: changepassword [OLD PASSWORD] [NEWPASSWORD] [CONFIRM NEW PASSWORD]\n"
            + "Example: " + COMMAND_WORD + " password helloworld helloworld\n"
            + "Example 2: " + COMMAND_ALIAS + " password helloworld hellowrold\n";

    public static final String MESSAGE_INVALID_INPUT = "Invalid Input.\n" + MESSAGE_SUCCESS;

    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    public ChangePasswordCommand(String oldPassword, String newPassword, String confirmPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
