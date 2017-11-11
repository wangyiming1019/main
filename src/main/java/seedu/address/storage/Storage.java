package seedu.address.storage;

import java.io.IOException;
import java.util.Optional;

import seedu.address.commons.events.model.AddressBookChangedEvent;
import seedu.address.commons.events.storage.DataSavingExceptionEvent;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(UserPrefs userPrefs) throws IOException;

    @Override
    String getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    //@@author charlesgoh
    /**
     * Backs up address book data at designated file name
     * @param addressBook
     * @param filePath
     */
    void backupAddressBook(ReadOnlyAddressBook addressBook, String filePath) throws IOException;

    /**
     * Initializes user preferences in storage manager
     */
    void initUserPrefs();

    /**
     * Unlocks addressbook by setting lock variable to false
     */
    void unlockAddressBook();

    /**
     * Locks address book by setting lock variable to true
     */
    void lockAddressBook();

    /**
     * Getter method for lock state for external classes
     */
    boolean getLockState();
    //@@author

    /**
     * Saves the current version of the Address Book to the hard disk.
     *   Creates the data file if it is missing.
     * Raises {@link DataSavingExceptionEvent} if there was an error during saving.
     */
    void handleAddressBookChangedEvent(AddressBookChangedEvent abce);
}
