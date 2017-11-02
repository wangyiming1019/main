# charlesgoh
###### \java\seedu\address\logic\commands\BackupCommand.java
``` java
public class BackupCommand extends Command {
    public static final String COMMAND_WORD = "backup";
    public static final String COMMAND_ALIAS = "bk";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Backs up data to a user input "
            + "location field [FILEPATH]\n"
            + "Parameter: KEYWORD [FILEPATH]\n"
            + "Example: " + COMMAND_WORD + " ~/Desktop";

    public static final String MESSAGE_INVALID_INPUT = "Invalid Input.\n";

    public static final String MESSAGE_SUCCESS = "AddressBook++ data backed up successfully.";

    private String args;

```
###### \java\seedu\address\logic\commands\BackupCommand.java
``` java
    public BackupCommand(String trimmedArgs) {
        super();
        this.args = trimmedArgs;
    }

    @Override
    public CommandResult execute() throws CommandException {
        requireNonNull(model);
        requireNonNull(model.getAddressBook());
        requireNonNull(storage);
        try {
            if (args.equals("")) {
                storage.backupAddressBookDefault(model.getAddressBook());
            } else {
                storage.backupAddressBook(model.getAddressBook(), args);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
```
###### \java\seedu\address\logic\commands\SortCommand.java
``` java
public class SortCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "sort";
    public static final String COMMAND_ALIAS = "so";

    public static final ArrayList<String> ACCEPTED_FIELD_PARAMETERS = new ArrayList<>(Arrays.asList(
            "name", "phone", "email", "address"));

    public static final ArrayList<String> ACCEPTED_ORDER_PARAMETERS = new ArrayList<>(Arrays.asList(
            "asc", "desc"));

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all persons by chosen "
                + "field [NAME/PHONE/EMAIL/ADDRESS] and by order [ASC/DESC]. Case insensitive\n"
                + "Parameters: KEYWORD [FIELD] [ORDER]\n"
                + "Example: " + COMMAND_WORD + " email desc";

    public static final String MESSAGE_INVALID_INPUT = "Invalid Input.\n"
            + "Accepted Field Values: NAME, PHONE, EMAIL, ADDRESS \n"
            + "Accepted Order Values: ASC, DESC";

    public static final String MESSAGE_SUCCESS = "All persons in address book successfully sorted";

    private final String field;
    private final String order;

```
###### \java\seedu\address\logic\commands\SortCommand.java
``` java
    public SortCommand(String field, String order) {
        this.field = field;
        this.order = order;
    }

    public String getField() {
        return this.field;
    }

    public String getOrder() {
        return this.order;
    }

    @Override
    public CommandResult executeUndoableCommand() {
        requireNonNull(model);
        model.sortPersons(getField(), getOrder());
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
```
###### \java\seedu\address\logic\parser\BackupCommandParser.java
``` java
public class BackupCommandParser implements Parser<BackupCommand> {

    @Override
    public BackupCommand parse(String userInput) throws ParseException {
        String trimmedArgs = userInput.trim();
        return new BackupCommand(trimmedArgs);
    }
}
```
###### \java\seedu\address\logic\parser\SortCommandParser.java
``` java
public class SortCommandParser implements Parser<SortCommand> {

    public static final int FIELD_ARG_POSITION = 0;
    public static final int ORDER_ARG_POSITION = 1;

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
```
###### \java\seedu\address\logic\parser\SortCommandParser.java
``` java
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        // Converts arg arrays to lower case to account for caps entries
        String[] argKeywords = trimmedArgs.split("\\s");
        for (int i = 0; i < argKeywords.length; i++) {
            argKeywords[i] = argKeywords[i].toLowerCase();
        }

        if (argKeywords.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        if (!SortCommand.ACCEPTED_FIELD_PARAMETERS.contains(argKeywords[FIELD_ARG_POSITION])
                    || !SortCommand.ACCEPTED_ORDER_PARAMETERS.contains(argKeywords[ORDER_ARG_POSITION])) {
            throw new ParseException(String.format(MESSAGE_INVALID_INPUT, SortCommand.MESSAGE_USAGE));
        }

        // If there are no problems with the input, return a new instance of SortCommand
        return new SortCommand(argKeywords[FIELD_ARG_POSITION], argKeywords[ORDER_ARG_POSITION]);

    }

}
```
###### \java\seedu\address\model\AddressBook.java
``` java
    /**
     * Sorts persons in person list by any field, in either ascending or descending order
     *
     * @param field
     * @param order
     */
    public void sortPersonsBy(String field, String order) {
        persons.sortBy(field, order);
    }
```
###### \java\seedu\address\model\person\Remark.java
``` java
public class Remark {
    public static final String MESSAGE_ADDRESS_CONSTRAINTS =
            "Person remarks can take any values, and it should not be blank";

    /*
     * The first character of the remark must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String REMARK_VALIDATION_REGEX = "[^\\s].*";
    public static final String REMARK_PLACEHOLDER_VALUE = "";

    public final String value;
    private boolean isPrivate = false;

    /**
     * Validates given remark.
     *
     * @throws IllegalValueException if given remark string is invalid.
     */
    public Remark(String remark) throws IllegalValueException {
        if (remark == null) {
            this.value = REMARK_PLACEHOLDER_VALUE;
            return;
        }
        if (!isValidRemark(remark)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        this.value = remark;
    }

    public Remark(String remark, boolean isPrivate) throws IllegalValueException {
        this(remark);
        this.setPrivate(isPrivate);
    }

    /**
     * Returns true if a given string is a valid person email.
     */
    public static boolean isValidRemark(String test) {
        return test.matches(REMARK_VALIDATION_REGEX) || test.equals(REMARK_PLACEHOLDER_VALUE);
    }

    @Override
    public String toString() {
        if (isPrivate) {
            return "<Private Remark>";
        }
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && this.value.equals(((Remark) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }
}
```
###### \java\seedu\address\model\person\UniquePersonList.java
``` java
public class UniquePersonList implements Iterable<Person> {

    private final ObservableList<Person> internalList = FXCollections.observableArrayList();
    // used by asObservableList()
    private final ObservableList<ReadOnlyPerson> mappedList = EasyBind.map(internalList, (person) -> person);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(ReadOnlyPerson toCheck) {
        requireNonNull(toCheck);
        return internalList.contains(toCheck);
    }

    /**
     * Adds a person to the list.
     *
     * @throws DuplicatePersonException if the person to add is a duplicate of an existing person in the list.
     */
    public void add(ReadOnlyPerson toAdd) throws DuplicatePersonException {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(new Person(toAdd));
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     *
     * @throws DuplicatePersonException if the replacement is equivalent to another existing person in the list.
     * @throws PersonNotFoundException if {@code target} could not be found in the list.
     */
    public void setPerson(ReadOnlyPerson target, ReadOnlyPerson editedPerson)
            throws DuplicatePersonException, PersonNotFoundException {
        requireNonNull(editedPerson);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.equals(editedPerson) && internalList.contains(editedPerson)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, new Person(editedPerson));
    }

    /**
     * Removes the equivalent person from the list.
     *
     * @throws PersonNotFoundException if no such person could be found in the list.
     */
    public boolean remove(ReadOnlyPerson toRemove) throws PersonNotFoundException {
        requireNonNull(toRemove);
        final boolean personFoundAndDeleted = internalList.remove(toRemove);
        if (!personFoundAndDeleted) {
            throw new PersonNotFoundException();
        }
        return personFoundAndDeleted;
    }
```
###### \java\seedu\address\model\person\UniquePersonList.java
``` java
    public void sortBy(String field, String order) {
        //sortyBy first chooses the right comparator
        Comparator<Person> comparator = null;

        /**
         * Comparators for the various fields available for sorting
         */
        Comparator<Person> personNameComparator = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getName().fullName.compareTo(o2.getName().fullName);
            }
        };

        Comparator<Person> personPhoneComparator = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getPhone().value.compareTo(o2.getPhone().value);
            }
        };

        Comparator<Person> personEmailComparator = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getEmail().value.compareTo(o2.getEmail().value);
            }
        };

        Comparator<Person> personAddressComparator = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAddress().value.compareTo(o2.getAddress().value);
            }
        };

        switch (field) {
        case "name":
            comparator = personNameComparator;
            break;

        case "phone":
            comparator = personPhoneComparator;
            break;

        case "email":
            comparator = personEmailComparator;
            break;

        case "address":
            comparator = personAddressComparator;
            break;

        default:
            try {
                System.out.println("An error occured");
                throw new Exception("Invalid field parameter entered...\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //sortBy then chooses the right ordering
        switch (order) {
        case "asc":
            Collections.sort(internalList, comparator);
            break;

        case "desc":
            Collections.sort(internalList, Collections.reverseOrder(comparator));
            break;

        default:
            try {
                System.out.println("An error occured");
                throw new Exception("Invalid field parameter entered...\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public Iterator<Person> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePersonList // instanceof handles nulls
                        && this.internalList.equals(((UniquePersonList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
```
###### \java\seedu\address\storage\AddressBookStorage.java
``` java
    /**
     * Saves the given {@link ReadOnlyAddressBook} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyAddressBook)
     */
    void saveAddressBook(ReadOnlyAddressBook addressBook, String filePath) throws IOException;

    void backupAddressBook(ReadOnlyAddressBook addressBook, String args) throws IOException;

    void backupAddressBookDefault(ReadOnlyAddressBook addressBook) throws IOException;

    void changeFilePath(String fp, UserPrefs u);
    //@author
}
```
###### \java\seedu\address\storage\StorageManager.java
``` java
    /**
     * Backs up data to a remote location.
     * @param addressBook
     * @param filePath
     * @throws IOException
     */
    public void backupAddressBook(ReadOnlyAddressBook addressBook, String filePath) throws IOException {
        logger.fine("Attempting to write to backup data file in custom location");
        this.saveAddressBook(addressBook, filePath);
    }

    /**
     * Default back up data which saves file in the same directory as the main save file
     * @param addressBook
     * @throws IOException
     */
    public void backupAddressBookDefault(ReadOnlyAddressBook addressBook) throws IOException {
        logger.fine("Attempting to write to backup data file");
        this.saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath() + "-backup.xml");
    }
```
###### \java\seedu\address\ui\MainWindow.java
``` java
    /**
     * Handle increase font size command
     */
    @FXML
    public void handleIncreaseFontSize() {
        logger.info("Handling increase in font size");
        personListPanel.increaseFontSize();
        taskListPanel.increaseFontSize();
    }

    /**
     * Handle decrease font size command
     */
    @FXML
    public void handleDecreaseFontSize() {
        logger.info("Handling increase in font size");
        personListPanel.decreaseFontSize();
        taskListPanel.decreaseFontSize();
    }

    /**
     * Handle reset font size command
     */
    @FXML
    public void handleResetFontSize() {
        logger.info("Handling increase in font size");
        personListPanel.resetFontSize();
        taskListPanel.resetFontSize();
    }
```
###### \java\seedu\address\ui\PersonCard.java
``` java
    /**
     * Set default size for all attributes
     */
    public void updateAttributeSizes() {
        nameSize = DEFAULT_NAME_SIZE + (fontSizeMultipler * FONT_SIZE_EXTENDER);
        attributeSize = DEFAULT_ATTRIBUTE_SIZE + (fontSizeMultipler * FONT_SIZE_EXTENDER);

        // Set styles using set name and attribute sizes
        name.setStyle("-fx-font-size: " + Integer.toString(nameSize));
        id.setStyle("-fx-font-size: " + Integer.toString(nameSize));
        phone.setStyle("-fx-font-size: " + Integer.toString(attributeSize));
        address.setStyle("-fx-font-size: " + Integer.toString(attributeSize));
        remark.setStyle("-fx-font-size: " + Integer.toString(attributeSize));
        email.setStyle("-fx-font-size: " + Integer.toString(attributeSize));
    }
```
###### \java\seedu\address\ui\PersonListPanel.java
``` java
    /**
     * Increases all person cards' font sizes in person list
     */
    public void increaseFontSize() {
        logger.info("PersonListPanel: Increasing font sizes");
        fontSizeMultiplier = Math.min(MAXIMUM_FONT_SIZE_MULTIPLIER, fontSizeMultiplier + 1);
        setConnections(personList);
    }

    /**
     * Decreases all person cards' font sizes in person list
     */
    public void decreaseFontSize() {
        logger.info("PersonListPanel: Decreasing font sizes");
        fontSizeMultiplier = Math.max(MINIMUM_FONT_SIZE_MULTIPLIER, fontSizeMultiplier - 1);
        setConnections(personList);
    }

    /**
     * Resets all person cards' font sizes in person list
     */
    public void resetFontSize() {
        logger.info("PersonListPanel: Resetting font sizes");
        fontSizeMultiplier = MINIMUM_FONT_SIZE_MULTIPLIER;
        setConnections(personList);
    }
```
###### \java\seedu\address\ui\TaskCard.java
``` java
    /**
     * Set default size for all attributes
     */
    public void updateAttributeSizes() {
        nameSize = DEFAULT_NAME_SIZE + (fontSizeMultipler * FONT_SIZE_EXTENDER);
        attributeSize = DEFAULT_ATTRIBUTE_SIZE + (fontSizeMultipler * FONT_SIZE_EXTENDER);

        // Set styles using set name and attribute sizes
        taskName.setStyle("-fx-font-size: " + Integer.toString(nameSize));
        id.setStyle("-fx-font-size: " + Integer.toString(nameSize));
        description.setStyle("-fx-font-size: " + Integer.toString(attributeSize));
        deadline.setStyle("-fx-font-size: " + Integer.toString(attributeSize));
        priority.setStyle("-fx-font-size: " + Integer.toString(attributeSize));
    }
```
###### \java\seedu\address\ui\TaskCard.java
``` java

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskCard)) {
            return false;
        }

        // state check
        TaskCard card = (TaskCard) other;
        return id.getText().equals(card.id.getText())
                && task.equals(card.task);
    }

    public Label getTaskName() {
        return taskName;
    }

    public Label getId() {
        return id;
    }

    public Label getDescription() {
        return description;
    }

    public Label getDeadline() {
        return deadline;
    }

    public Label getPriority() {
        return priority;
    }

    public void setFontSizeMultiplier(int fontSizeMultipler) {
        this.fontSizeMultipler = fontSizeMultipler;
    }

    public int getFontSizeMultiplier() {
        return this.fontSizeMultipler;
    }
}
```
###### \java\seedu\address\ui\TaskListPanel.java
``` java
    /**
     * Increases all task cards' font sizes in person list
     */
    public void increaseFontSize() {
        logger.info("TaskListPanel: Increasing font sizes");
        fontSizeMultiplier = Math.min(MAXIMUM_FONT_SIZE_MULTIPLIER, fontSizeMultiplier + 1);
        setConnections(taskList);
    }

    /**
     * Decreases all task cards' font sizes in person list
     */
    public void decreaseFontSize() {
        logger.info("TaskListPanel: Decreasing font sizes");
        fontSizeMultiplier = Math.max(MINIMUM_FONT_SIZE_MULTIPLIER, fontSizeMultiplier - 1);
        setConnections(taskList);
    }

    /**
     * Resets all task cards' font sizes in person list
     */
    public void resetFontSize() {
        logger.info("TaskListPanel: Resetting font sizes");
        fontSizeMultiplier = MINIMUM_FONT_SIZE_MULTIPLIER;
        setConnections(taskList);
    }
```
###### \resources\view\MainWindow.fxml
``` fxml
      <Menu mnemonicParsing="false" text="Font Size">
        <items>
          <MenuItem fx:id="increaseSizeMenuItem" mnemonicParsing="false" onAction="#handleIncreaseFontSize" text="Increase +" />
            <MenuItem fx:id="decreaseSizeMenuItem" mnemonicParsing="false" onAction="#handleDecreaseFontSize" text="Decrease -" />
            <MenuItem fx:id="resetSizeMenuItem" mnemonicParsing="false" onAction="#handleResetFontSize" text="Reset" />
        </items>
      </Menu>
```
