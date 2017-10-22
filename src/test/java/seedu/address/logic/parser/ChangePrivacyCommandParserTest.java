package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.Test;

import seedu.address.logic.commands.ChangePrivacyCommand;

public class ChangePrivacyCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangePrivacyCommand.MESSAGE_USAGE);

    private ChangePrivacyCommandParser parser = new ChangePrivacyCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", ChangePrivacyCommand.MESSAGE_NO_FIELDS);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // Non boolean argument
        assertParseFailure(parser, "1" + " " + PREFIX_NAME + "notBoolean",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangePrivacyCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1" + " " + PREFIX_PHONE + "notBoolean",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangePrivacyCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1" + " " + PREFIX_EMAIL + "notBoolean",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangePrivacyCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1" + " " + PREFIX_ADDRESS + "notBoolean",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangePrivacyCommand.MESSAGE_USAGE));

        // valid value followed by invalid value. The test case for invalid value  followed by valid value
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + " " + PREFIX_NAME + "true" + " " + PREFIX_NAME + "notBoolean",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangePrivacyCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1" + " " + PREFIX_PHONE + "true" + " " + PREFIX_PHONE + "notBoolean",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangePrivacyCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1" + " " + PREFIX_EMAIL + "true" + " " + PREFIX_EMAIL + "notBoolean",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangePrivacyCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1" + " " + PREFIX_ADDRESS + "true" + " " + PREFIX_ADDRESS + "notBoolean",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangePrivacyCommand.MESSAGE_USAGE));
    }
}
