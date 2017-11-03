package seedu.address.logic.commands;

//@@author jeffreygohkw
import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.BrowserPanelNavigateEvent;
import seedu.address.logic.commands.exceptions.CommandException;


/**
 * Locates a person's address on Google Maps identified using it's last displayed index from the address book.
 */
public class NavigateCommand extends Command {

    public static final String COMMAND_WORD = "navigate";
    public static final String COMMAND_ALIAS = "nav";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Get directions from one address to another.\n"
            + "Parameters: [fp/INDEX] OR [ft/INDEX] (must be a positive integer) OR [fa/ADDRESS]"
            + " AND [tp/INDEX] OR [tt/INDEX] OR [ta/ADDRESS]\n"
            + "Example: " + COMMAND_WORD + " fp/2 ta/University Town";

    public static final String MESSAGE_NAVIGATE_SUCCESS = "Navigating from %1$s to %1$s";

    private final String locationFrom;
    private final String locationTo;

    public NavigateCommand(String from, String to) {
        this.locationFrom = from;
        this.locationTo = to;
    }

    @Override
    public CommandResult execute() throws CommandException {

        EventsCenter.getInstance().post(new BrowserPanelNavigateEvent(locationFrom, locationTo));
        return new CommandResult(String.format(MESSAGE_NAVIGATE_SUCCESS, locationFrom, locationTo));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LocateCommand // instanceof handles nulls
                && this.locationFrom.equals(((NavigateCommand) other).locationFrom)
                && this.locationTo.equals(((NavigateCommand) other).locationTo)); // state check
    }
}
