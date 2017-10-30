package seedu.address.ui;

import java.util.HashMap;
import java.util.Random;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.ReadOnlyPerson;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    public static final int DEFAULT_NAME_SIZE = 20;
    // public static final int DEFAULT_TAG_SIZE = 14;
    public static final int DEFAULT_ATTRIBUTE_SIZE = 15;

    private static final String FXML = "PersonListCard.fxml";
    /**
     * Preset values for random selection later.
     */
    private enum Colours {
        blue, green, brown, purple, navy, crimson, firebrick, maroon, red
    }
    private static HashMap<String, String> colourHash = new HashMap<String, String>();
    private static Random randomNumber = new Random();

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final ReadOnlyPerson person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label remark;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;

    private int nameSize = DEFAULT_NAME_SIZE;
    private int attributeSize = DEFAULT_ATTRIBUTE_SIZE;

    private int fontSizeMultipler;

    public PersonCard(ReadOnlyPerson person, int displayedIndex, int fontSizeMultiplier) {
        super(FXML);
        this.person = person;
        this.fontSizeMultipler = fontSizeMultiplier;
        id.setText(displayedIndex + ". ");
        initTags(person);
        bindListeners(person);
        updateAttributeSizes();
    }

    /**
     * Binds the individual UI elements to observe their respective {@code Person} properties
     * so that they will be notified of any changes.
     */
    private void bindListeners(ReadOnlyPerson person) {
        name.textProperty().bind(Bindings.convert(person.nameProperty()));
        phone.textProperty().bind(Bindings.convert(person.phoneProperty()));
        address.textProperty().bind(Bindings.convert(person.addressProperty()));
        remark.textProperty().bind(Bindings.convert(person.remarkProperty()));
        email.textProperty().bind(Bindings.convert(person.emailProperty()));
        person.tagProperty().addListener((observable, oldValue, newValue) -> {
            tags.getChildren().clear();
            initTags(person);
        });
    }

    /**
     * Locate hashed colour for tag. If not found, new colour is assigned to tag
     * @param tag
     * @return
     */
    private String getTagColour(String tag) {
        if (!colourHash.containsKey(tag)) {
            int randomiser = randomNumber.nextInt(Colours.values().length);
            String colour = Colours.values()[randomiser].toString();
            colourHash.put(tag, colour);
        }
        return colourHash.get(tag);
    }

    /**
     * Assigns each tag a colour
     * @param person
     */
    private void initTags(ReadOnlyPerson person) {
        person.getTags().forEach(tag -> {
            Label newTagLabel = new Label(tag.getTagName());

            newTagLabel.setStyle("-fx-background-color: " + this.getTagColour(tag.getTagName()));

            tags.getChildren().add(newTagLabel);
        });
    }

    /**
     * Set default size for all attributes
     */
    public void updateAttributeSizes() {
        nameSize += fontSizeMultipler * 5;
        attributeSize += fontSizeMultipler * 5;
        id.setStyle("-fx-font-size: " + Integer.toString(attributeSize));
        name.setStyle("-fx-font-size: " + Integer.toString(nameSize));
        phone.setStyle("-fx-font-size: " + Integer.toString(attributeSize));
        address.setStyle("-fx-font-size: " + Integer.toString(attributeSize));
        remark.setStyle("-fx-font-size: " + Integer.toString(attributeSize));
        email.setStyle("-fx-font-size: " + Integer.toString(attributeSize));
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }

    public Label getName() {
        return name;
    }

    public Label getId() {
        return id;
    }

    public Label getPhone() {
        return phone;
    }

    public Label getAddress() {
        return address;
    }

    public Label getRemark() {
        return remark;
    }

    public Label getEmail() {
        return email;
    }
}
