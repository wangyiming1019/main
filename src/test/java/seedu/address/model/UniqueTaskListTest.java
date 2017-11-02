package seedu.address.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.task.UniqueTaskList;

//@@author charlesgoh
public class UniqueTaskListTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        UniqueTaskList uniquePersonList = new UniqueTaskList();
        thrown.expect(UnsupportedOperationException.class);
        uniquePersonList.asObservableList().remove(0);
    }
}
