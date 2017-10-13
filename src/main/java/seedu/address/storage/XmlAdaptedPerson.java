package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.tag.Tag;

/**
 * JAXB-friendly version of the Person.
 */
public class XmlAdaptedPerson {

    @XmlElement(required = true)
    private String name;
    private Boolean nameIsPrivate;
    @XmlElement(required = true)
    private String phone;
    private Boolean phoneIsPrivate;
    @XmlElement(required = true)
    private String email;
    private Boolean emailIsPrivate;
    @XmlElement(required = true)
    private String address;
    private Boolean addressIsPrivate;

    @XmlElement
    private List<XmlAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs an XmlAdaptedPerson.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedPerson() {}


    /**
     * Converts a given Person into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedPerson
     */
    public XmlAdaptedPerson(ReadOnlyPerson source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;

        nameIsPrivate = source.getName().isPrivate();
        phoneIsPrivate = source.getPhone().isPrivate();
        emailIsPrivate = source.getEmail().isPrivate();
        addressIsPrivate = source.getAddress().isPrivate();

        tagged = new ArrayList<>();
        for (Tag tag : source.getTags()) {
            tagged.add(new XmlAdaptedTag(tag));
        }
    }

    /**
     * Converts this jaxb-friendly adapted person object into the model's Person object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (XmlAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }
        final Name name = new Name(this.name, this.nameIsPrivate);
        final Phone phone = new Phone(this.phone, this.phoneIsPrivate);
        final Email email = new Email(this.email, this.emailIsPrivate);
        final Address address = new Address(this.address, this.addressIsPrivate);
        final Set<Tag> tags = new HashSet<>(personTags);
        return new Person(name, phone, email, address, tags);
    }
}
