package seedu.address.logic.parser;

import seedu.address.logic.commands.BackupCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input save location and saves a backup copy of the address book data there.
 */
public class BackupCommandParser implements Parser<BackupCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the BackupCommand
     * and returns a Backup object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public BackupCommand parse(String args) throws ParseException {
        return new BackupCommand(args);
    }
}
