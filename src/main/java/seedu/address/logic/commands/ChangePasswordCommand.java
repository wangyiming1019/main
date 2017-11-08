package seedu.address.logic.commands;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import com.google.common.hash.Hashing;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.UserPrefs;

//@@author charlesgoh
/**
 * Changes user's password provided old password is correct and new passwords match.
 */
public class ChangePasswordCommand extends Command {
    public static final String COMMAND_WORD = "changepassword";
    public static final String COMMAND_ALIAS = "cps";

    public static final String MESSAGE_SUCCESS = "Password changed successfully";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Changes user password. \n"
            + "Format: changepassword [OLD PASSWORD] [NEWPASSWORD] [CONFIRM NEW PASSWORD]\n"
            + "Example: " + COMMAND_WORD + " password mynewpassword111 mynewpassword111\n"
            + "Example 2: " + COMMAND_ALIAS + " password mynewpassword111 mynewpassword111\n";

    public static final String MESSAGE_INVALID_INPUT = "Invalid Input.\n";

    public static final String MESSAGE_PASSWORD_CONFIRMATION_INCORRECT = "Your new passwords do not match\n";

    private final Logger logger = LogsCenter.getLogger(ChangePasswordCommand.class);

    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    public ChangePasswordCommand(String oldPassword, String newPassword, String confirmPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    private String forwardHash(String argument) {
        return Hashing.sha256().hashString(argument, StandardCharsets.UTF_8).toString();
    }

    /**
     * Forward hashes the user input password and checks if it matches with the encrypted password saved
     */
    private boolean isOldPasswordCorrect() {
        String forwardHashedInputPassword = forwardHash(oldPassword);
        String forwardHashActualPassword;
        UserPrefs userPrefs;
        try {
            userPrefs = storage.readUserPrefs().get();
            forwardHashActualPassword = userPrefs.getAddressBookEncryptedPassword();
            if (forwardHashActualPassword.equals(forwardHashedInputPassword)) {
                logger.info("Actual password and input password matches");
                return true;
            } else {
                logger.warning("Actual password and input password do not match");
                return false;
            }
        } catch (DataConversionException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Takes new input passwords and checks them against one another.
     */
    private boolean isNewPasswordInputsSame() {
        return forwardHash(newPassword).equals(confirmPassword);
    }

    @Override
    public CommandResult execute() {

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
