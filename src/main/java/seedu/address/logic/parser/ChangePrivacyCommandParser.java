package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.ChangePrivacyCommand;
import seedu.address.logic.commands.ChangePrivacyCommand.PersonPrivacySettings;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ChangePrivacyCommand object
 */
public class ChangePrivacyCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ChangePrivacyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());

        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangePrivacyCommand.MESSAGE_USAGE));
        }

        PersonPrivacySettings pps = new PersonPrivacySettings();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            if (argMultimap.getValue(PREFIX_NAME).equals("true")) {
                pps.setNameIsPrivate(true);
            } else if (argMultimap.getValue(PREFIX_NAME).equals("false")) {
                pps.setNameIsPrivate(false);
            }
        }

        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            if (argMultimap.getValue(PREFIX_PHONE).equals("true")) {
                pps.setPhoneIsPrivate(true);
            } else if (argMultimap.getValue(PREFIX_PHONE).equals("false")) {
                pps.setPhoneIsPrivate(false);
            }
        }

        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            if (argMultimap.getValue(PREFIX_EMAIL).equals("true")) {
                pps.setEmailIsPrivate(true);
            } else if (argMultimap.getValue(PREFIX_EMAIL).equals("false")) {
                pps.setEmailIsPrivate(false);
            }
        }

        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            if (argMultimap.getValue(PREFIX_ADDRESS).equals("true")) {
                pps.setAddressIsPrivate(true);
            } else if (argMultimap.getValue(PREFIX_ADDRESS).equals("false")) {
                pps.setAddressIsPrivate(false);
            }
        }

        if (!pps.isAnyFieldNonNull()) {
            throw new ParseException(ChangePrivacyCommand.MESSAGE_NO_FIELDS);
        }

        return new ChangePrivacyCommand(index, pps);
    }
}
