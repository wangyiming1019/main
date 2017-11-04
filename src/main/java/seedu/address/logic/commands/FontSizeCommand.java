package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.SortCommand.MESSAGE_SUCCESS_TASKS;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Sorts all persons in address book by any field. Sorting can be done in ascending or descending order
 */
//@@author charlesgoh
public class FontSizeCommand extends Command {

    public static final String COMMAND_WORD = "fontsize";
    public static final String COMMAND_ALIAS = "fs";

    public static final ArrayList<String> ACCEPTED_PARAMETERS = new ArrayList<>(Arrays.asList(
            "+", "-", "reset"));

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Increases, decreases or resets font sizes \n"
                + "Parameters: KEYWORD [OPTION]\n"
                + "Example: " + COMMAND_WORD + " fontsize +\n"
                + "Example 2: " + COMMAND_WORD + " fs reset\n";

    public static final String MESSAGE_INVALID_INPUT = "Invalid Input.\n" + MESSAGE_USAGE;

    public static final String MESSAGE_SUCCESS_FONT = "Font size increased successfully";

    private final String option;

    //@@author charlesgoh
    public FontSizeCommand(String option) {
        this.option = option;
    }

    public String getOption() {
        return this.option;
    }

    @Override
    public CommandResult execute() {
        requireNonNull(model);
        Boolean done = false;

        if (getOption().equals(ACCEPTED_PARAMETERS.get(0))) {
            mainWindow.increaseFontSize();
        } else if (getOption().equals(ACCEPTED_PARAMETERS.get(1))) {
            mainWindow.decreaseFontSize();
        } else if (getOption().equals(ACCEPTED_PARAMETERS.get(2))){
            mainWindow.resetFontSize();
        }

        return new CommandResult(MESSAGE_SUCCESS_FONT);
    }

}
