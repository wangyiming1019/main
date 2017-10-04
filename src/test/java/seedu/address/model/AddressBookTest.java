package seedu.address.model;

import static org.junit.Assert.assertEquals;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

import java.util.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.tag.Tag;

public class AddressBookTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
        assertEquals(Collections.emptyList(), addressBook.getTagList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        addressBook.resetData(null);
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsAssertionError() {
        // Repeat ALICE twice
        List<Person> newPersons = Arrays.asList(new Person(ALICE), new Person(ALICE));
        List<Tag> newTags = new ArrayList<>(ALICE.getTags());
        AddressBookStub newData = new AddressBookStub(newPersons, newTags);

        thrown.expect(AssertionError.class);
        addressBook.resetData(newData);
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        addressBook.getPersonList().remove(0);
    }

    @Test
    public void getTagList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        addressBook.getTagList().remove(0);
    }

    @Test
    public void sortCommandLogic_name_asc() {
        AddressBook testBook = getTypicalAddressBook();
        testBook.sortPersonsBy("name", "asc");
        ObservableList<ReadOnlyPerson> sortedTestList = testBook.getPersonList();


        List<ReadOnlyPerson> expectedList = getTypicalPersons();
        Collections.sort(expectedList, Comparator.comparing(o -> o.getName().toString()));
        ObservableList<ReadOnlyPerson> sortedexpectedList = FXCollections.observableArrayList(expectedList);

        assertEquals(sortedTestList, sortedexpectedList);
    }

    @Test
    public void sortCommandLogic_name_desc() {
        AddressBook testBook = getTypicalAddressBook();
        testBook.sortPersonsBy("name", "desc");
        ObservableList<ReadOnlyPerson> sortedTestList = testBook.getPersonList();


        List<ReadOnlyPerson> expectedList = getTypicalPersons();
        Collections.sort(expectedList, Collections.reverseOrder(Comparator.comparing(o -> o.getName().toString())));
        ObservableList<ReadOnlyPerson> sortedexpectedList = FXCollections.observableArrayList(expectedList);

        assertEquals(sortedTestList, sortedexpectedList);
    }

    @Test
    public void sortCommandLogic_phone_asc() {
        AddressBook testBook = getTypicalAddressBook();
        testBook.sortPersonsBy("phone", "asc");
        ObservableList<ReadOnlyPerson> sortedTestList = testBook.getPersonList();


        List<ReadOnlyPerson> expectedList = getTypicalPersons();
        Collections.sort(expectedList, Comparator.comparing(o -> o.getPhone().toString()));
        ObservableList<ReadOnlyPerson> sortedexpectedList = FXCollections.observableArrayList(expectedList);

        assertEquals(sortedTestList, sortedexpectedList);
    }

    @Test
    public void sortCommandLogic_phone_desc() {
        AddressBook testBook = getTypicalAddressBook();
        testBook.sortPersonsBy("phone", "desc");
        ObservableList<ReadOnlyPerson> sortedTestList = testBook.getPersonList();


        List<ReadOnlyPerson> expectedList = getTypicalPersons();
        Collections.sort(expectedList, Collections.reverseOrder(Comparator.comparing(o -> o.getPhone().toString())));
        ObservableList<ReadOnlyPerson> sortedexpectedList = FXCollections.observableArrayList(expectedList);

        assertEquals(sortedTestList, sortedexpectedList);
    }

    @Test
    public void sortCommandLogic_email_asc() {
        AddressBook testBook = getTypicalAddressBook();
        testBook.sortPersonsBy("email", "asc");
        ObservableList<ReadOnlyPerson> sortedTestList = testBook.getPersonList();


        List<ReadOnlyPerson> expectedList = getTypicalPersons();
        Collections.sort(expectedList, Comparator.comparing(o -> o.getEmail().toString()));
        ObservableList<ReadOnlyPerson> sortedexpectedList = FXCollections.observableArrayList(expectedList);

        assertEquals(sortedTestList, sortedexpectedList);
    }

    @Test
    public void sortCommandLogic_email_desc() {
        AddressBook testBook = getTypicalAddressBook();
        testBook.sortPersonsBy("email", "desc");
        ObservableList<ReadOnlyPerson> sortedTestList = testBook.getPersonList();


        List<ReadOnlyPerson> expectedList = getTypicalPersons();
        Collections.sort(expectedList, Collections.reverseOrder(Comparator.comparing(o -> o.getEmail().toString())));
        ObservableList<ReadOnlyPerson> sortedexpectedList = FXCollections.observableArrayList(expectedList);

        assertEquals(sortedTestList, sortedexpectedList);
    }

    @Test
    public void sortCommandLogic_address_asc() {
        AddressBook testBook = getTypicalAddressBook();
        testBook.sortPersonsBy("address", "asc");
        ObservableList<ReadOnlyPerson> sortedTestList = testBook.getPersonList();


        List<ReadOnlyPerson> expectedList = getTypicalPersons();
        Collections.sort(expectedList, Comparator.comparing(o -> o.getAddress().toString()));
        ObservableList<ReadOnlyPerson> sortedexpectedList = FXCollections.observableArrayList(expectedList);

        assertEquals(sortedTestList, sortedexpectedList);
    }

    @Test
    public void sortCommandLogic_address_desc() {
        AddressBook testBook = getTypicalAddressBook();
        testBook.sortPersonsBy("address", "desc");
        ObservableList<ReadOnlyPerson> sortedTestList = testBook.getPersonList();


        List<ReadOnlyPerson> expectedList = getTypicalPersons();
        Collections.sort(expectedList, Collections.reverseOrder(Comparator.comparing(o -> o.getAddress().toString())));
        ObservableList<ReadOnlyPerson> sortedexpectedList = FXCollections.observableArrayList(expectedList);

        assertEquals(sortedTestList, sortedexpectedList);
    }

    /**
     * A stub ReadOnlyAddressBook whose persons and tags lists can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<ReadOnlyPerson> persons = FXCollections.observableArrayList();
        private final ObservableList<Tag> tags = FXCollections.observableArrayList();

        AddressBookStub(Collection<? extends ReadOnlyPerson> persons, Collection<? extends Tag> tags) {
            this.persons.setAll(persons);
            this.tags.setAll(tags);
        }

        @Override
        public ObservableList<ReadOnlyPerson> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Tag> getTagList() {
            return tags;
        }
    }

}
