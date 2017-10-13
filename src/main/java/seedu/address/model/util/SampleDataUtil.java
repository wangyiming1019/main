package seedu.address.model.util;

import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        try {
            return new Person[] {
                new Person(new Name("Alex Yeoh", false), new Phone("87438807", false), new Email("alexyeoh@example.com", false),
                    new Address("Blk 30 Geylang Street 29, #06-40", false),
                    getTagSet("friends")),
                new Person(new Name("Bernice Yu", false), new Phone("99272758", false), new Email("berniceyu@example.com", false),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18", false),
                    getTagSet("colleagues", "friends")),
                new Person(new Name("Charlotte Oliveiro", false), new Phone("93210283", false), new Email("charlotte@example.com", false),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04", false),
                    getTagSet("neighbours")),
                new Person(new Name("David Li", false), new Phone("91031282", false), new Email("lidavid@example.com", false),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43", false),
                    getTagSet("family")),
                new Person(new Name("Irfan Ibrahim", false), new Phone("92492021", false), new Email("irfan@example.com", false),
                    new Address("Blk 47 Tampines Street 20, #17-35", false),
                    getTagSet("classmates")),
                new Person(new Name("Roy Balakrishnan", false), new Phone("92624417", false), new Email("royb@example.com", false),
                    new Address("Blk 45 Aljunied Street 85, #11-31", false),
                    getTagSet("colleagues"))
            };
        } catch (IllegalValueException e) {
            throw new AssertionError("sample data cannot be invalid", e);
        }
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        try {
            AddressBook sampleAb = new AddressBook();
            for (Person samplePerson : getSamplePersons()) {
                sampleAb.addPerson(samplePerson);
            }
            return sampleAb;
        } catch (DuplicatePersonException e) {
            throw new AssertionError("sample data cannot contain duplicate persons", e);
        }
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) throws IllegalValueException {
        HashSet<Tag> tags = new HashSet<>();
        for (String s : strings) {
            tags.add(new Tag(s));
        }

        return tags;
    }

}
