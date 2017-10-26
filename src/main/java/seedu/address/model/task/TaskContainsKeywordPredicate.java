package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code ReadOnlyTask}'s {@code TaskName} or {@code Description} matches any of the keywords given.
 */
public class TaskContainsKeywordPredicate  implements Predicate<ReadOnlyTask> {
    private final List<String> keywords;

    public TaskContainsKeywordPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(ReadOnlyTask task) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getTaskName().taskName, keyword)
                || StringUtil.containsWordIgnoreCase(task.getDescription().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskContainsKeywordPredicate // instanceof handles nulls
                && this.keywords.equals(((TaskContainsKeywordPredicate) other).keywords)); // state check
    }
}
