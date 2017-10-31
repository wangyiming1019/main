package seedu.address.logic.parser;

//@@author Esilocke

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TARGET;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import java.util.ArrayList;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AssignTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/** Parses input arguments, and creates a new {@code AssignTaskCommand} object**/
public class AssignTaskCommandParser implements Parser<AssignTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AssignTaskCommand
     * and returns an AssignTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AssignTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TARGET);
        if (!argMultimap.getValue(PREFIX_TARGET).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignTaskCommand.MESSAGE_USAGE));
        }
        String target = argMultimap.getValue(PREFIX_TARGET).get();
        String persons = argMultimap.getPreamble();
        ArrayList<Index> targetIndexes = parseIndexes(target);
        ArrayList<Index> personIndexes = parseIndexes(persons);
        if (targetIndexes.size() != 1) {
            throw new ParseException(AssignTaskCommand.MESSAGE_INVALID_TARGET_ARGS);
        } else if (personIndexes.size() < 1) {
            throw new ParseException(AssignTaskCommand.MESSAGE_INVALID_PERSONS_ARGS);
        }
        Index taskIndex = targetIndexes.get(0);
        return new AssignTaskCommand(personIndexes, taskIndex);
    }

    /**
     *   Parses the given {@code String} and returns an ArrayList of Indexes that correspond to
     *   the value in the String.
     *   @throws ParseException if any of the values in the String cannot be converted into an {@code Index}
     */
    private ArrayList<Index> parseIndexes(String args) throws ParseException {
        String[] splitted = args.split(" ");
        ArrayList<Index> targetsToAdd = new ArrayList<>();
        int parsedInt;
        try {
            for (String s : splitted) {
                parsedInt = Integer.parseInt(s);
                targetsToAdd.add(Index.fromOneBased(parsedInt));
            }
        } catch (NumberFormatException nfe) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return targetsToAdd;
    }
}
