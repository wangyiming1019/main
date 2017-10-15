package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.ReadOnlyPerson;

/**
 * Changes the privacy setting of a person's details in the address book
 */
public class ChangePrivacyCommand extends UndoableCommand {
    public static final String COMMAND_WORD = "changeprivacy";
    public static final String COMMAND_ALIAS = "cp";

    public static final String TRUE_WORD = "true";
    public static final String FALSE_WORD = "false";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Changes the privacy of the details of the person"
            + "identified by the index number used in the last person listing.\n "
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + TRUE_WORD + " OR " + FALSE_WORD + "]"
            + "[" + PREFIX_PHONE + TRUE_WORD + " OR " + FALSE_WORD + "]"
            + "[" + PREFIX_EMAIL + TRUE_WORD + " OR " + FALSE_WORD + "]"
            + "[" + PREFIX_ADDRESS + TRUE_WORD + " OR " + FALSE_WORD + "]"
            + "[" + PREFIX_TAG + TRUE_WORD + " OR " + FALSE_WORD + "]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + TRUE_WORD
            + PREFIX_EMAIL + FALSE_WORD;

    public static final String MESSAGE_CHANGE_PRIVACY_SUCCESS = "Changed the Privacy of the Person: %1$s";
    public static final String MESSAGE_NO_FIELDS = "At least one field to change must be provided.";

    private final Index index;
    private final personPrivacySettings pps;

    /**
     * @param index of the person in the filtered person list to change the privacy of
     */
    public ChangePrivacyCommand(Index index, personPrivacySettings pps) {
        requireNonNull(index);
        requireNonNull(pps);

        this.index = index;
        this.pps = pps;
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        List<ReadOnlyPerson> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        ReadOnlyPerson personToChange = lastShownList.get(index.getZeroBased());

        changePersonPrivacy(personToChange, pps);

        return new CommandResult(String.format(MESSAGE_CHANGE_PRIVACY_SUCCESS, personToChange));
    }

    /**
     * Changes a person's fields' privacy
     * @param person the person whose privacy we would like to change
     * @param pps the settings of privacy for each field
     */
    private static void changePersonPrivacy(ReadOnlyPerson person, personPrivacySettings pps) {
        person.getName().setPrivate(pps.nameIsPrivate());
        person.getAddress().setPrivate(pps.addressIsPrivate());
        person.getEmail().setPrivate(pps.emailIsPrivate());
        person.getPhone().setPrivate(pps.phoneIsPrivate());
    }

    /**
     * Stores the privacy settings for each field of a person.
     */
    public static class personPrivacySettings {
        private boolean nameIsPrivate;
        private boolean phoneIsPrivate;
        private boolean emailIsPrivate;
        private boolean addressIsPrivate;

        public personPrivacySettings() {

        }

        public boolean nameIsPrivate() {
            return nameIsPrivate;
        }

        public void setNameIsPrivate(boolean nameIsPrivate) {
            this.nameIsPrivate = nameIsPrivate;
        }

        public boolean phoneIsPrivate() {
            return phoneIsPrivate;
        }

        public void setPhoneIsPrivate(boolean phoneIsPrivate) {
            this.phoneIsPrivate = phoneIsPrivate;
        }

        public boolean emailIsPrivate() {
            return emailIsPrivate;
        }

        public void setEmailIsPrivate(boolean emailIsPrivate) {
            this.emailIsPrivate = emailIsPrivate;
        }

        public boolean addressIsPrivate() {
            return addressIsPrivate;
        }

        public void setAddressIsPrivate(boolean addressIsPrivate) {
            this.addressIsPrivate = addressIsPrivate;
        }
    }
}
