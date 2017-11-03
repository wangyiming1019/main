package seedu.address.ui;

import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.TaskPanelSelectionChangedEvent;
import seedu.address.model.task.ReadOnlyTask;

/**
 * The Browser Panel of the App.
 */
public class ViewTaskPanel extends UiPart<Region> {

    private static final String FXML = "ViewTaskPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(this.getClass());

    @FXML
    private Label taskName;
    @FXML
    private Label description;
    @FXML
    private Label deadline;
    @FXML
    private Label priority;

    private ReadOnlyTask task;

    public ViewTaskPanel(ReadOnlyTask task) {
        super(FXML);

        if (task == null) {
            this.task = task;
            initializeWithBlankValues();
        } else {
            initializeWithTask(task);
        }
        registerAsAnEventHandler(this);
    }

    public ViewTaskPanel() {
        super(FXML);
        initializeWithBlankValues();
        registerAsAnEventHandler(this);
    }

    /**
     * Binds the individual UI elements to observe their respective {@code Task} properties
     * so that they will be notified of any changes.
     */
    private void initializeWithTask(ReadOnlyTask task) {
        taskName.textProperty().bind(Bindings.convert(task.taskNameProperty()));
        description.textProperty().bind(Bindings.convert(task.descriptionProperty()));
        deadline.textProperty().bind(Bindings.convert(task.deadlineProperty()));
        priority.textProperty().bind(Bindings.convert(task.priorityProperty()));
    }

    /**
     * Initializes an empty Task Panel
     */
    private void initializeWithBlankValues() {

    }

    @Subscribe
    private void handleTaskPanelSelectionChangedEvent(TaskPanelSelectionChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        initializeWithTask(event.getNewSelection().task);
    }
}
