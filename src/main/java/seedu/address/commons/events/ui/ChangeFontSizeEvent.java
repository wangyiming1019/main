package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;

/**
 * Represents a font size change triggered by a command or the UI
 */
//author charlesgoh
public class ChangeFontSizeEvent extends BaseEvent {

    private static int INCREASE_SIZE_EVENT_INDEX = 0;
    private static int DECREASE_SIZE_EVENT_INDEX = 1;
    private static int RESET_SIZE_EVENT_INDEX = 2;

    private int triggerOption;

    public ChangeFontSizeEvent(int triggerOption) {
        this.triggerOption = triggerOption;
    }

    @Override
    public String toString() {
        return "New Event: Font size change";
    }

    public static int getIncreaseSizeEventIndex() {
        return INCREASE_SIZE_EVENT_INDEX;
    }

    public static int getDecreaseSizeEventIndex() {
        return DECREASE_SIZE_EVENT_INDEX;
    }

    public static int getResetSizeEventIndex() {
        return RESET_SIZE_EVENT_INDEX;
    }

    public int getTriggerOption() {
        return triggerOption;
    }

    public void setTriggerOption(int triggerOption) {
        this.triggerOption = triggerOption;
    }
}
