package seedu.address.logic.commands;
//@@author jeffreygohkw
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.ShowAllPrivacyLevelPredicate;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Handles the changing of Privacy Levels in the address book
 */
public class PrivacyLevelCommand extends Command {

    public static final String COMMAND_WORD = "privacylevel";
    public static final String COMMAND_ALIAS = "pl";

    public static final String CHANGE_PRIVACY_LEVEL_SUCCESS = "Successfully change privacy level to %1$s.";
    public static final String WRONG_PRIVACY_LEVEL_MESSAGE = "Privacy Level can only be 0, 1 or 2";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Changes the privacy level of the address book. Level 0 shows all data, level 1 hides private fields"
            + " and level 2 hides people with at least 1 private field.\n"
            + "Parameters: LEVEL (must be 1, 2 or 3)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final int level;

    public PrivacyLevelCommand(int level) {
        this.level = level;
    }

    @Override
    public CommandResult execute() throws CommandException {
        if (level < 1 || level > 3) {
            throw new CommandException(WRONG_PRIVACY_LEVEL_MESSAGE);
        }
        model.setPrivacyLevel(level);
        for (int i = 0; i < model.getAddressBook().getPersonList().size(); i++) {
            ReadOnlyPerson toReplace = model.getAddressBook().getPersonList().get(i);
            Person newPerson = new Person(model.getAddressBook().getPersonList().get(i));
            newPerson.setPrivacyLevel(level);
            try {
                model.updatePerson(toReplace, newPerson);
            } catch (DuplicatePersonException e) {
                throw new CommandException(MESSAGE_DUPLICATE_PERSON);
            } catch (PersonNotFoundException e) {
                throw new AssertionError("The target person cannot be missing");
            }
        }
        if (level == 3) {
            model.updateFilteredPersonList(new ShowAllPrivacyLevelPredicate());
        } else {
            model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        }
        return new CommandResult(String.format(CHANGE_PRIVACY_LEVEL_SUCCESS, Integer.toString(level)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PrivacyLevelCommand // instanceof handles nulls
                && this.level == ((PrivacyLevelCommand) other).level); // state check
    }
}
