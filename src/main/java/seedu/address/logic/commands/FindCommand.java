package seedu.address.logic.commands;

import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.task.TaskContainsKeywordPredicate;

/**
 * Finds and lists all persons or tasks in address book whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String COMMAND_ALIAS = "f";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";
    public static final String MESSAGE_TASK_USAGE = COMMAND_WORD + ": Finds all tasks whose names or descriptions "
            + "contain any of the specified keywords (case-sensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " task make";

    private final NameContainsKeywordsPredicate personPredicate;
    private final TaskContainsKeywordPredicate taskPredicate;
    private boolean isTask;

    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.personPredicate = predicate;
        this.taskPredicate = null;
        this.isTask = false;
    }

    public FindCommand(TaskContainsKeywordPredicate predicate) {
        this.personPredicate = null;
        this.taskPredicate = predicate;
        this.isTask = true;
    }

    @Override
    public CommandResult execute() {
        if (isTask) {
            assert(taskPredicate != null);
            model.updateFilteredTaskList(taskPredicate);
            return new CommandResult(getMessageForTaskListShownSummary(model.getFilteredTaskList().size()));
        } else {
            assert(personPredicate != null);
            model.updateFilteredPersonList(personPredicate);
            return new CommandResult(getMessageForPersonListShownSummary(model.getFilteredPersonList().size()));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof FindCommand)) {
            return false;
        } else if (this.isTask != ((FindCommand) other).isTask) {
            return false;
        } else if (this.isTask) {
            return this.taskPredicate.equals(((FindCommand) other).taskPredicate);
        } else {
            return this.personPredicate.equals(((FindCommand) other).personPredicate);
        }
    }
}
