package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

//@@author Esilocke
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
        for (int i = 0; i < keywords.size(); i++) {
            String keyword = keywords.get(i);
            if (StringUtil.containsWordIgnoreCase(task.getTaskName().taskName, keyword)
                    || StringUtil.containsWordIgnoreCase(task.getDescription().value, keyword)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskContainsKeywordPredicate // instanceof handles nulls
                && this.keywords.equals(((TaskContainsKeywordPredicate) other).keywords)); // state check
    }
}
