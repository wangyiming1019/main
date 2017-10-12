package seedu.address.model.task;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DescriptionTest {

    @Test
    public void isValidDescription() {
        // invalid descriptions
        assertFalse(Decription.isValidDecription(" ")); // spaces only

        // valid descriptions
        assertTrue(Decription.isValidDecription("")); // empty string
        assertTrue(Decription.isValidDecription("alphanumerical contents")); // alphanumerical contents
        assertTrue(Decription.isValidDecription("-")); // single character
    }
}
