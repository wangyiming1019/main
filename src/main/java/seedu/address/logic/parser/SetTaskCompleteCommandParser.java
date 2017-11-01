package seedu.address.logic.parser;

//@@author Esilocke

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.SetTaskCompleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/** Parses input arguments and returns a {@code SetTaskCompleteCommand} that changes the state of the given command */
public class SetTaskCompleteCommandParser implements Parser<SetTaskCompleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SetTaskCompleteCommand
     * and returns an SetTaskCompleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetTaskCompleteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new SetTaskCompleteCommand(index);
        } catch (IllegalValueException ive) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetTaskCompleteCommand.MESSAGE_USAGE));
        }
    }
}
