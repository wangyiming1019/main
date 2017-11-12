package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code ReadOnlyPerson}'s matches any one of the keywords
 */
public class PersonCompleteMatchPredicate implements Predicate<ReadOnlyPerson> {
    private final List<ReadOnlyPerson> keywords;

    public PersonCompleteMatchPredicate(List<ReadOnlyPerson> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(ReadOnlyPerson person) {
        return keywords.stream()
                .anyMatch(keyword -> person.equals(keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonCompleteMatchPredicate // instanceof handles nulls
                && this.keywords.equals(((PersonCompleteMatchPredicate) other).keywords)); // state check
    }

}
