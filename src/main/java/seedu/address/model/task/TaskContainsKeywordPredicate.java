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
    private boolean needFilterByState;
    private boolean needFilterByPriority;
    private boolean isComplete;
    private int basePriority;

    public TaskContainsKeywordPredicate(List<String> keywords, boolean isStateCheckRequired,
                                        boolean isPriorityCheckRequired, boolean isComplete, int basePriority) {
        this.keywords = keywords;
        this.needFilterByPriority = isPriorityCheckRequired;
        this.needFilterByState = isStateCheckRequired;
        this.isComplete = isComplete;
        this.basePriority = basePriority;
    }

    @Override
    public boolean test(ReadOnlyTask task) {
        for (int i = 0; i < keywords.size(); i++) {
            String keyword = keywords.get(i);
            if (needFilterByState && task.getCompleteState() != isComplete) {
                return false;
            } else if (needFilterByPriority && task.getPriority().value < basePriority) {
                return false;
            } else {
                return (StringUtil.containsWordIgnoreCase(task.getTaskName().taskName, keyword)
                        || StringUtil.containsWordIgnoreCase(task.getDescription().value, keyword));
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
