package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import org.junit.Test;

import seedu.address.logic.commands.SelectPersonCommand;
import seedu.address.logic.commands.SelectTaskCommand;

/**
 * Test scope: similar to {@code DeletePersonCommandParserTest}.
 * @see DeletePersonCommandParserTest
 */
public class SelectPersonCommandParserTest {

    private SelectCommandParser parser = new SelectCommandParser();

    @Test
    public void parse_validArgs_returnsSelectCommand() {
        assertParseSuccess(parser, "1", new SelectPersonCommand(INDEX_FIRST_PERSON));
        assertParseSuccess(parser, " " + PREFIX_TASK.getPrefix() + " 1",
                new SelectTaskCommand(INDEX_FIRST_TASK));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SelectPersonCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " " + PREFIX_TASK.getPrefix() + " -1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectTaskCommand.MESSAGE_USAGE));
    }
}
