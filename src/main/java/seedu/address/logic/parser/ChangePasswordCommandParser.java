package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ChangePasswordCommand;
import seedu.address.logic.parser.exceptions.ParseException;

//@@author charlesgoh
/**
 * Parses input arguments and creates a new ChangePassword object
 */
public class ChangePasswordCommandParser implements Parser<ChangePasswordCommand> {

    public static final int OLD_PASSWORD_POSITION = 0;
    public static final int NEW_PASSWORD_POSITION = 1;
    public static final int CONFIRM_PASSWORD_POSITION = 2;
    public static final int SIZE_OF_ARG_ARRAY = 3;

    /**
     * Parses the given {@code String} of arguments in the context of the ChangePasswordCommand
     * and returns a ChangePasswordCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ChangePasswordCommand parse(String args) throws ParseException {
        //System.out.println(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String
                    .format(MESSAGE_INVALID_COMMAND_FORMAT, ChangePasswordCommand.MESSAGE_USAGE));
        }

        String[] argKeywords = trimmedArgs.split("\\s");
        // System.out.println(Integer.toString(argKeywords.length));

        if (argKeywords.length != SIZE_OF_ARG_ARRAY
                || (argKeywords[0].isEmpty())
                || (argKeywords[1].isEmpty())
                || (argKeywords[2].isEmpty())) {
            throw new ParseException(String
                    .format(MESSAGE_INVALID_COMMAND_FORMAT, ChangePasswordCommand.MESSAGE_USAGE));
        }

        // If there are no problems with the input, return a new instance of SortCommand
        return new ChangePasswordCommand(argKeywords[OLD_PASSWORD_POSITION],
                argKeywords[NEW_PASSWORD_POSITION], argKeywords[CONFIRM_PASSWORD_POSITION]);

    }
}
