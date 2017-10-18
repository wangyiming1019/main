package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;

import seedu.address.MainApp;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.storage.Storage;

/**
 * Backs up current addressbook into a user input location.
 */
public class BackupCommand extends Command {
    public static final String COMMAND_WORD = "backup";
    public static final String COMMAND_ALIAS = "bk";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Backs up data to a user input "
            + "location field [FILEPATH]\n"
            + "Parameter: KEYWORD [FILEPATH]\n"
            + "Example: " + COMMAND_WORD + " ~/Desktop";

    public static final String MESSAGE_INVALID_INPUT = "Invalid Input.\n";

    public static final String MESSAGE_SUCCESS = "AddressBook++ data backed up successfully.";

    private String filepath;
    private Storage storage;
    private MainApp mainapp;

    public BackupCommand(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public CommandResult execute() throws CommandException {
        //TODO: Figure out how to access storage component
        requireNonNull(model);
        try {
            storage.backupAddressBookToLocation(model.getAddressBook(), filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
