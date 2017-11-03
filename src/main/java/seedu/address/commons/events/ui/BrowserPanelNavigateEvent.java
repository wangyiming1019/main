package seedu.address.commons.events.ui;

//@@author jeffreygohkw

import seedu.address.commons.events.BaseEvent;

/**
 * Represents a selection change in the Person List Panel
 */
public class BrowserPanelNavigateEvent extends BaseEvent {

    private final String fromLocation;
    private final String toLocation;

    public BrowserPanelNavigateEvent(String fromLocation, String toLocation) {
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }
}
