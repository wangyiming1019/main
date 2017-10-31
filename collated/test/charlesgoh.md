# charlesgoh
###### /java/seedu/address/ui/PersonCardTest.java
``` java
    @Test
    public void editFontSizeTests() {
        int fontSizeMultiplier = PersonCard.DEFAULT_FONT_SIZE_MULTIPLIER;
        Person testPerson = new PersonBuilder().build();
        PersonCard personCard = new PersonCard(testPerson, 1, fontSizeMultiplier);
        assertEquals(PersonCard.DEFAULT_FONT_SIZE_MULTIPLIER, personCard.getFontSizeMultipler());
        assertNotEquals(personCard.getFontSizeMultipler(), fontSizeMultiplier + 1);

        // Verify font size increase
        fontSizeMultiplier = PersonCard.DEFAULT_FONT_SIZE_MULTIPLIER + 1;
        personCard.setFontSizeMultipler(fontSizeMultiplier);
        assertEquals(personCard.getFontSizeMultipler(), fontSizeMultiplier);
        assertNotEquals(personCard.getFontSizeMultipler(), PersonCard.DEFAULT_FONT_SIZE_MULTIPLIER);

        // Verify font size decrease
        fontSizeMultiplier = PersonCard.DEFAULT_FONT_SIZE_MULTIPLIER - 1;
        personCard.setFontSizeMultipler(fontSizeMultiplier);
        assertEquals(personCard.getFontSizeMultipler(), fontSizeMultiplier);
        assertNotEquals(personCard.getFontSizeMultipler(), PersonCard.DEFAULT_FONT_SIZE_MULTIPLIER);
    }
```
###### /java/guitests/guihandles/TaskCardHandle.java
``` java
public class TaskCardHandle extends NodeHandle<Node> {
    private static final String ID_FIELD_ID = "#id";
    private static final String TASK_NAME_ID = "#taskName";
    private static final String DESCRIPTION_ID = "#description";
    private static final String DEADLINE_ID = "#deadline";
    private static final String PRIORITY_ID = "#priority";

    private final Label idLabel;
    private final Label taskNameLabel;
    private final Label descriptionLabel;
    private final Label deadlineLabel;
    private final Label priorityLabel;

    public TaskCardHandle(Node cardNode) {
        super(cardNode);

        this.idLabel = getChildNode(ID_FIELD_ID);
        this.taskNameLabel = getChildNode(TASK_NAME_ID);
        this.descriptionLabel = getChildNode(DESCRIPTION_ID);
        this.priorityLabel = getChildNode(PRIORITY_ID);
        this.deadlineLabel = getChildNode(DEADLINE_ID);
    }

    public String getId() {
        return idLabel.getText();
    }

    public String getTaskName() {
        return taskNameLabel.getText();
    }

    public String getDescription() {
        return descriptionLabel.getText();
    }

    public String getDeadline() {
        return deadlineLabel.getText();
    }

    public String getPriority() {
        return priorityLabel.getText();
    }
}
```
###### /java/guitests/guihandles/TaskListPanelHandle.java
``` java
public class TaskListPanelHandle extends NodeHandle<ListView<TaskCard>> {
    public static final String TASK_LIST_VIEW_ID = "#taskListView";

    private Optional<TaskCard> lastRememberedSelectedTaskCard;

    public TaskListPanelHandle(ListView<TaskCard> taskListPanelNode) {
        super(taskListPanelNode);
    }

    /**
     * Returns a handle to the selected {@code TaskCardHandle}.
     * A maximum of 1 item can be selected at any time.
     * @throws AssertionError if no card is selected, or more than 1 card is selected.
     */
    public TaskCardHandle getHandleToSelectedCard() {
        List<TaskCard> taskList = getRootNode().getSelectionModel().getSelectedItems();

        if (taskList.size() != 1) {
            throw new AssertionError("Task list size expected 1.");
        }

        return new TaskCardHandle(taskList.get(0).getRoot());
    }

    /**
     * Returns the index of the selected card.
     */
    public int getSelectedCardIndex() {
        return getRootNode().getSelectionModel().getSelectedIndex();
    }

    /**
     * Returns true if a card is currently selected.
     */
    public boolean isAnyCardSelected() {
        List<TaskCard> selectedCardsList = getRootNode().getSelectionModel().getSelectedItems();

        if (selectedCardsList.size() > 1) {
            throw new AssertionError("Card list size expected 0 or 1.");
        }

        return !selectedCardsList.isEmpty();
    }

    /**
     * Navigates the listview to display and select the task.
     */
    public void navigateToCard(ReadOnlyTask task) {
        List<TaskCard> cards = getRootNode().getItems();
        Optional<TaskCard> matchingCard = cards.stream().filter(card -> card.task.equals(task)).findFirst();

        if (!matchingCard.isPresent()) {
            throw new IllegalArgumentException("Task does not exist.");
        }

        guiRobot.interact(() -> {
            getRootNode().scrollTo(matchingCard.get());
            getRootNode().getSelectionModel().select(matchingCard.get());
        });
        guiRobot.pauseForHuman();
    }

    /**
     * Returns the task card handle of a task associated with the {@code index} in the list.
     */
    public TaskCardHandle getTaskCardHandle(int index) {
        return getTaskCardHandle(getRootNode().getItems().get(index).task);
    }

    /**
     * Returns the {@code TaskCardHandle} of the specified {@code task} in the list.
     */
    public TaskCardHandle getTaskCardHandle(ReadOnlyTask task) {
        Optional<TaskCardHandle> handle = getRootNode().getItems().stream()
                .filter(card -> card.task.equals(task))
                .map(card -> new TaskCardHandle(card.getRoot()))
                .findFirst();
        return handle.orElseThrow(() -> new IllegalArgumentException("Task does not exist."));
    }

    /**
     * Selects the {@code TaskCard} at {@code index} in the list.
     */
    public void select(int index) {
        getRootNode().getSelectionModel().select(index);
    }

    /**
     * Remembers the selected {@code TaskCard} in the list.
     */
    public void rememberSelectedTaskCard() {
        List<TaskCard> selectedItems = getRootNode().getSelectionModel().getSelectedItems();

        if (selectedItems.size() == 0) {
            lastRememberedSelectedTaskCard = Optional.empty();
        } else {
            lastRememberedSelectedTaskCard = Optional.of(selectedItems.get(0));
        }
    }

    /**
     * Returns true if the selected {@code TaskCard} is different from the value remembered by the most recent
     * {@code rememberSelectedTaskCard()} call.
     */
    public boolean isSelectedTaskCardChanged() {
        List<TaskCard> selectedItems = getRootNode().getSelectionModel().getSelectedItems();

        if (selectedItems.size() == 0) {
            return lastRememberedSelectedTaskCard.isPresent();
        } else {
            return !lastRememberedSelectedTaskCard.isPresent()
                    || !lastRememberedSelectedTaskCard.get().equals(selectedItems.get(0));
        }
    }

    /**
     * Returns the size of the list.
     */
    public int getListSize() {
        return getRootNode().getItems().size();
    }
}
```
