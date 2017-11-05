package seedu.address.logic.parser;

//@@author jeffreygohkw
import static org.junit.Assert.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.SortCommand.ACCEPTED_FIELD_PARAMETERS;
import static seedu.address.logic.commands.SortCommand.ACCEPTED_LIST_PARAMETERS;
import static seedu.address.logic.commands.SortCommand.ACCEPTED_ORDER_PARAMETERS;
import static seedu.address.logic.commands.SortCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void no_arguments_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    //author charlesgoh
    @Test
    public void parse_wrongArguments_failure() {
        // no list specified
        for (String field: ACCEPTED_FIELD_PARAMETERS) {
            for (String order: ACCEPTED_ORDER_PARAMETERS) {
                assertParseFailure(parser, field + " " + order,
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
            }
        }

        // no field specified
        for (String list: ACCEPTED_LIST_PARAMETERS) {
            for (String order: ACCEPTED_ORDER_PARAMETERS) {
                assertParseFailure(parser, list + " " + order,
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
            }
        }

        // no order specified
        for (String list: ACCEPTED_LIST_PARAMETERS) {
            for (String field: ACCEPTED_FIELD_PARAMETERS) {
                assertParseFailure(parser, list + " " + field,
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
            }
        }

        // Incorrect test
        assertParseFailure(parser, "random text",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));

    }

    @Test
    public void parse_validArguments_success() throws ParseException {
        SortCommand expectedCommand;
        SortCommand actualCommand;

        // For person sorts
        String list = ACCEPTED_LIST_PARAMETERS.get(0);
        for (String field: ACCEPTED_FIELD_PARAMETERS.subList(0, 4)) {
            for (String order : ACCEPTED_ORDER_PARAMETERS) {
                expectedCommand = new SortCommand(list, field, order);

                // Commands for full sort command word
                actualCommand = parser.parse(list + " " + field + " " + order);
                assertEquals(expectedCommand.getList(), actualCommand.getList());
                assertEquals(expectedCommand.getField(), actualCommand.getField());
                assertEquals(expectedCommand.getOrder(), actualCommand.getOrder());

                // Commands for sort command alias
                actualCommand = parser.parse(list + " " + field + " " + order);
                assertEquals(expectedCommand.getList(), actualCommand.getList());
                assertEquals(expectedCommand.getField(), actualCommand.getField());
                assertEquals(expectedCommand.getOrder(), actualCommand.getOrder());
            }
        }

        // For task sorts
        list = ACCEPTED_LIST_PARAMETERS.get(1);
        for (String field: ACCEPTED_FIELD_PARAMETERS.subList(4, 6)) {
            for (String order: ACCEPTED_ORDER_PARAMETERS) {
                expectedCommand = new SortCommand(list, field, order);

                // Commands for full sort command word
                actualCommand = parser.parse(list + " " + field + " " +  order);
                assertEquals(expectedCommand.getList(), actualCommand.getList());
                assertEquals(expectedCommand.getField(), actualCommand.getField());
                assertEquals(expectedCommand.getOrder(), actualCommand.getOrder());

                // Commands for sort command alias
                actualCommand = parser.parse(list + " " + field + " " +  order);
                assertEquals(expectedCommand.getList(), actualCommand.getList());
                assertEquals(expectedCommand.getField(), actualCommand.getField());
                assertEquals(expectedCommand.getOrder(), actualCommand.getOrder());
            }
        }
    }
    //@@author charlesgoh
}
