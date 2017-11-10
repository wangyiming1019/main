package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.TASK_SEPARATOR;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import org.junit.Test;

import seedu.address.logic.commands.DeletePersonCommand;
import seedu.address.logic.commands.DeleteTaskCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeletePersonCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeletePersonCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeletePersonCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeletePersonCommand(INDEX_FIRST_PERSON));
    }

    //@@author Esilocke
    @Test
    public void parseTaskValidArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, TASK_SEPARATOR + "1",
                new DeleteTaskCommand(INDEX_FIRST_TASK));
    }
    //@@author

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePersonCommand.MESSAGE_USAGE));
    }

    //@@author Esilocke
    @Test
    public void parseTaskInvalidArgs_throwsParseException() {
        assertParseFailure(parser, TASK_SEPARATOR + "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE));
    }
    //@@author
}
