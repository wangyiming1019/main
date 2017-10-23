package seedu.address.logic.commands;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static seedu.address.logic.commands.ChangePrivacyCommand.PersonPrivacySettings;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonPrivacySettingsBuilder;

public class ChangePrivacyCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void personPrivacySettingsTests() {
        PersonPrivacySettings pps = new PersonPrivacySettings();

        assertFalse(pps.isAnyFieldNonNull());

        PersonPrivacySettings ppsByBuilder = new PersonPrivacySettingsBuilder().setNamePrivate("true")
            .setPhonePrivate("false").setEmailPrivate("true").setAddressPrivate("true").build();

        pps.setNameIsPrivate(true);
        pps.setPhoneIsPrivate(false);
        pps.setEmailIsPrivate(true);
        pps.setAddressIsPrivate(true);

        assertEquals(ppsByBuilder.getAddressIsPrivate(), pps.getAddressIsPrivate());
        assertEquals(ppsByBuilder.getEmailIsPrivate(), pps.getEmailIsPrivate());
        assertEquals(ppsByBuilder.getNameIsPrivate(), pps.getNameIsPrivate());
        assertEquals(ppsByBuilder.getPhoneIsPrivate(), pps.getPhoneIsPrivate());
        assertEquals(ppsByBuilder.isAnyFieldNonNull(), pps.isAnyFieldNonNull());
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() throws Exception {
        Person newPerson = new PersonBuilder().withEmail("alice@example.com").build();
        newPerson.getEmail().setPrivate(true);

        PersonPrivacySettings pps = new PersonPrivacySettingsBuilder(newPerson).setNamePrivate("false")
                .setPhonePrivate("false").setEmailPrivate("true").setAddressPrivate("false").build();
        ChangePrivacyCommand changePrivacyCommand = new ChangePrivacyCommand(INDEX_FIRST_PERSON, pps);
        changePrivacyCommand.model = model;

        String expectedMessage = String.format(ChangePrivacyCommand.MESSAGE_CHANGE_PRIVACY_SUCCESS, newPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        expectedModel.updatePerson(model.getFilteredPersonList().get(0), newPerson);

        assertCommandSuccess(changePrivacyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() throws Exception {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        ReadOnlyPerson lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        Person personInList = new PersonBuilder(lastPerson).build();
        personInList.getName().setPrivate(true);
        personInList.getPhone().setPrivate(true);

        PersonPrivacySettings pps = new PersonPrivacySettingsBuilder(personInList).setNamePrivate("true")
                .setPhonePrivate("true").build();
        ChangePrivacyCommand changePrivacyCommand = new ChangePrivacyCommand(indexLastPerson, pps);
        changePrivacyCommand.model = model;

        String expectedMessage = String.format(ChangePrivacyCommand.MESSAGE_CHANGE_PRIVACY_SUCCESS, personInList);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        expectedModel.updatePerson(lastPerson, personInList);

        assertCommandSuccess(changePrivacyCommand, model, expectedMessage, expectedModel);
    }
}
