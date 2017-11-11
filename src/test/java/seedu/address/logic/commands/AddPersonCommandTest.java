package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.ReadOnlyTask;
import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TaskBuilder;
import seedu.address.ui.MainWindow;


public class AddPersonCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingObjectAdded modelStub = new ModelStubAcceptingObjectAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = getAddCommandForPerson(validPerson, modelStub).execute();

        assertEquals(String.format(AddPersonCommand.MESSAGE_SUCCESS, validPerson), commandResult.feedbackToUser);
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_taskAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingObjectAdded modelStub = new ModelStubAcceptingObjectAdded();
        Task validTask = new TaskBuilder().build();

        CommandResult commandResult = getAddCommandForTask(validTask, modelStub).execute();

        assertEquals(String.format(AddTaskCommand.MESSAGE_SUCCESS, validTask), commandResult.feedbackToUser);
        assertEquals(Arrays.asList(validTask), modelStub.tasksAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() throws Exception {
        ModelStub modelStub = new ModelStubAlwaysThrowingDuplicateException();
        Person validPerson = new PersonBuilder().build();

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddPersonCommand.MESSAGE_DUPLICATE_PERSON);

        getAddCommandForPerson(validPerson, modelStub).execute();
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() throws Exception {
        ModelStub modelStub = new ModelStubAlwaysThrowingDuplicateException();
        Task validTask = new TaskBuilder().build();

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddTaskCommand.MESSAGE_DUPLICATE_TASK);

        getAddCommandForTask(validTask, modelStub).execute();
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        Task paper = new TaskBuilder().withTaskName("Paper").build();
        Task pencil = new TaskBuilder().withTaskName("Pencil").build();

        AddPersonCommand addAliceCommand = new AddPersonCommand(alice);
        AddPersonCommand addBobCommand = new AddPersonCommand(bob);
        AddTaskCommand addPaperCommand = new AddTaskCommand(paper);
        AddTaskCommand addPencilCommand = new AddTaskCommand(pencil);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));
        assertTrue(addPaperCommand.equals(addPaperCommand));

        // same values -> returns true
        AddPersonCommand addAliceCommandCopy = new AddPersonCommand(alice);
        AddTaskCommand addPaperCommandCopy = new AddTaskCommand(paper);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));
        assertTrue(addPaperCommand.equals(addPaperCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));
        assertFalse(addPaperCommand.equals(""));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));
        assertFalse(addPaperCommand.equals(null));

        // different person/task -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
        assertFalse(addPaperCommand.equals(addPencilCommand));

        // add person vs add task -> returns false
        assertFalse(addAliceCommand.equals(addPaperCommand));
    }

    /**
     * Generates a new AddPersonCommand with the details of the given person.
     */
    private AddPersonCommand getAddCommandForPerson(Person person, Model model) {
        AddPersonCommand command = new AddPersonCommand(person);
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        return command;
    }

    /**
     * Generates a new AddPersonCommand with the details of the given person.
     */
    private AddTaskCommand getAddCommandForTask(Task task, Model model) {
        AddTaskCommand command = new AddTaskCommand(task);
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        return command;
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void addPerson(ReadOnlyPerson person) throws DuplicatePersonException {
            fail("This method should not be called.");
        }

        @Override
        public void addTag(Tag toAdd, ArrayList<Index> targetIndexes)  {
            fail("This method should not be called.");
        }

        @Override
        public void resetData(ReadOnlyAddressBook newData) {
            fail("This method should not be called.");
        }

        @Override
        public void resetPartialData(ReadOnlyAddressBook newData, Prefix prefix) {
            fail("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            fail("This method should not be called.");
            return null;
        }

        //@@author charlesgoh
        public void increaseFontSize() {
            fail("This method should not be called.");
        }

        public void decreaseFontSize() {
            fail("This method should not be called.");
        }

        public void resetFontSize() {
            fail("This method should not be called.");
        }

        public void setMainWindow(MainWindow mainWindow) {
            fail("This method should not be called.");
        }
        //@@author

        @Override
        public void deletePerson(ReadOnlyPerson target) throws PersonNotFoundException {
            fail("This method should not be called.");
        }

        @Override
        public void deleteTag(Tag toDelete, ArrayList<Index> targetIndexes) {
            fail("This method should not be called.");
        }

        @Override
        public void updatePerson(ReadOnlyPerson target, ReadOnlyPerson editedPerson)
                throws DuplicatePersonException {
            fail("This method should not be called.");
        }

        @Override
        public void editTag(Tag toChange, Tag newTag, ArrayList<Index> affectedIndexes) throws PersonNotFoundException,
                DuplicatePersonException {
            fail("This method should not be called.");
        }

        @Override
        public ObservableList<ReadOnlyPerson> getFilteredPersonList() {
            fail("This method should not be called.");
            return null;
        }

        @Override
        public void updateFilteredPersonList(Predicate<ReadOnlyPerson> predicate) {
            fail("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Predicate<ReadOnlyTask> predicate) {
            fail("This method should not be called.");
        }

        @Override
        public void sortPersons(String field, String order) {
            fail("This method should not be called.");
        }

        @Override
        public void sortTasks(String field, String order) {
            fail("This method should not be called.");
        }

        @Override
        public void favouritePerson(ReadOnlyPerson target) throws PersonNotFoundException {
            fail("This method should not be called.");
        }

        @Override
        public void addTask(ReadOnlyTask toAdd) throws DuplicateTaskException {
            fail("This method should not be called.");
        }

        @Override
        public void unfavouritePerson(ReadOnlyPerson target) throws PersonNotFoundException {
            fail("This method should not be called.");
        }

        @Override
        public void deleteTask(ReadOnlyTask toDelete) throws TaskNotFoundException {
            fail("This method should not be called.");
        }

        @Override
        public ObservableList<ReadOnlyTask> getFilteredTaskList() {
            fail("This method should not be called.");
            return null;
        }

        @Override
        public void updateTask(ReadOnlyTask target, ReadOnlyTask editedTask) throws DuplicateTaskException {
            fail("This method should not be called.");
        }

        @Override
        public void assignToTask(ArrayList<ReadOnlyPerson> personsToAssign, ReadOnlyTask taskToAssign)
                throws TaskNotFoundException, DuplicateTaskException {
            fail("This method should not be called.");
        }

        @Override
        public void dismissFromTask(ArrayList<ReadOnlyPerson> personsToDismiss, ReadOnlyTask taskToDismiss)
                throws TaskNotFoundException, DuplicateTaskException {
            fail("This method should not be called.");
        }

        @Override
        public void setAsComplete(ReadOnlyTask toSet, boolean isComplete)
                throws TaskNotFoundException, DuplicateTaskException {
            fail("This method should not be called.");
        }

        @Override
        public void setPrivacyLevel(int level) {
            fail("This method should not be called.");
        }

        @Override
        public int getPrivacyLevel() {
            fail("This method should not be called.");
            return 0;
        }

        @Override
        public ReadOnlyPerson getPersonAtIndexFromAddressBook(int index) {
            fail("This method should not be called.");
            return null;
        }
    }

    /**
     * A Model stub that always throw a DuplicateDataException when trying to add a person or task.
     */
    private class ModelStubAlwaysThrowingDuplicateException extends ModelStub {
        @Override
        public void addPerson(ReadOnlyPerson person) throws DuplicatePersonException {
            throw new DuplicatePersonException();
        }

        @Override
        public void addTask(ReadOnlyTask task) throws DuplicateTaskException {
            throw new DuplicateTaskException();
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

    /**
     * A Model stub that always accept the person or task being added.
     */
    private class ModelStubAcceptingObjectAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();
        final ArrayList<Task> tasksAdded = new ArrayList<>();

        @Override
        public void addPerson(ReadOnlyPerson person) throws DuplicatePersonException {
            personsAdded.add(new Person(person));
        }

        @Override
        public void addTask(ReadOnlyTask task) throws DuplicateTaskException {
            tasksAdded.add(new Task(task));
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
