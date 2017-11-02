package seedu.address.model.task;

import java.util.Date;
import java.util.List;

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;

import seedu.address.commons.exceptions.IllegalValueException;
//@@author Esilocke
/**
 * Represents the deadline of a task in the address book.
 */
public class Deadline {
    public static final String MESSAGE_INVALID_DATE =
            "The specified date is invalid.";
    public static final String DEADLINE_PLACEHOLDER_VALUE = "";
    /*
    Deadline format: DDSMMSYYYY, in DAY-MONTH-YEAR format.
    S represents the separators, and can be any of these characters: - . /
     */
    public static final String DEADLINE_VALIDATION_REGEX = "\\d\\d[-./]\\d\\d[-./]\\d\\d\\d\\d.*";
    private static final String DEADLINE_PERIOD_DELIMITER = ".";

    /*
    Expected indexes for the separator characters
     */
    private static final int DEADLINE_SEPARATOR_INDEX_1 = 2;
    private static final int DEADLINE_SEPARATOR_INDEX_2 = 5;
    private static final int DEADLINE_DAY_INDEX = 0;
    private static final int DEADLINE_MONTH_INDEX = 1;
    private static final int DEADLINE_YEAR_INDEX = 2;

    public final Date date;
    public final String value;

    /**
     * Validates given deadline.
     *
     * @throws IllegalValueException if given deadline string is invalid.
     */
    public Deadline(String deadline) throws IllegalValueException {
        if (deadline == null) {
            this.value = DEADLINE_PLACEHOLDER_VALUE;
            this.date = null;
            return;
        } else if (deadline.equals(DEADLINE_PLACEHOLDER_VALUE)) {
            this.value = DEADLINE_PLACEHOLDER_VALUE;
            this.date = null;
            return;
        }
        this.date = setDateFromArgs(deadline);
        this.value = date.toString();
    }

    /**
     * Returns true if the given string is a valid date.
     * Guarantees: given string format is valid
     */
    public static boolean isValidDeadline(String test) {
        if (test.equals(DEADLINE_PLACEHOLDER_VALUE)) {
            return true;
        }
        try {
            Date testDate = setDateFromArgs(test);
            return true;
        } catch (IllegalValueException e) {
            return false;
        }
    }

    /**
     * Returns a Date object that represents the given date string.
     */
    private static Date setDateFromArgs(String date) throws IllegalValueException {
        Parser deadlineParser = new Parser();
        List<DateGroup> groups = deadlineParser.parse(date);
        List<Date> dates = null;
        for (DateGroup group : groups) {
            dates = group.getDates();
        }
        if (dates == null) {
            throw new IllegalValueException(MESSAGE_INVALID_DATE);
        } else {
            return dates.get(0);
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && this.value.equals(((Deadline) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
