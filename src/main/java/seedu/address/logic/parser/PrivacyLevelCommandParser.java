package seedu.address.logic.parser;
//@@author jeffreygohkw
import static seedu.address.logic.commands.PrivacyLevelCommand.WRONG_PRIVACY_LEVEL_MESSAGE;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.PrivacyLevelCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new PrivacyLevelCommand object
 */
public class PrivacyLevelCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the PrivacyLevelCommand
     * and returns an PrivacyLevelCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PrivacyLevelCommand parse(String args) throws ParseException {
        try {
            int level = ParserUtil.parseIndex(args).getOneBased();
            return new PrivacyLevelCommand(level);
        } catch (IllegalValueException ive) {
            throw new ParseException(
                    String.format(WRONG_PRIVACY_LEVEL_MESSAGE, PrivacyLevelCommand.MESSAGE_USAGE));
        }
    }
}
