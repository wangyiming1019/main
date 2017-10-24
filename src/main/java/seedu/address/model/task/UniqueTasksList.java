package seedu.address.model.task;

import java.util.Iterator;

import org.fxmisc.easybind.EasyBind;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Provides a list of Tasks that are unique, and are not null.
 */
public class UniqueTasksList implements Iterable<Task> {
    private final ObservableList<Task> internalList = FXCollections.observableArrayList();
    // used by asObservableList()
    private final ObservableList<ReadOnlyTask> mappedList = EasyBind.map(internalList, (task) -> task);

    public ObservableList<ReadOnlyTask> asObservableList() {
        return FXCollections.unmodifiableObservableList(mappedList);
    }

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }
}
