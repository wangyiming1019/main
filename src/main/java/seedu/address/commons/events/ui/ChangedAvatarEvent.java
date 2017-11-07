package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.person.ReadOnlyPerson;

/**
 * Represents an avatar change event
 */
//author charlesgoh
public class ChangedAvatarEvent extends BaseEvent {

    private ReadOnlyPerson targetPerson;

    public ChangedAvatarEvent(ReadOnlyPerson person) {
        this.targetPerson = person;
    }

    public ReadOnlyPerson getTargetPerson() {
        return targetPerson;
    }

    public void setTargetPerson(ReadOnlyPerson targetPerson) {
        this.targetPerson = targetPerson;
    }

    @Override
    public String toString() {
        return "New Event: Avatar changed";
    }
}
