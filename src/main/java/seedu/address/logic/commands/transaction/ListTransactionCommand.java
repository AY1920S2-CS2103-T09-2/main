package seedu.address.logic.commands.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TRANSACTIONS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all transactions in the application to the user.
 */
public class ListTransactionCommand extends Command {
    public static final String COMMAND_WORD = "listt";

    public static final String MESSAGE_SUCCESS = "Listed all transactions";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTransactionList(PREDICATE_SHOW_ALL_TRANSACTIONS);
        System.out.println("List " + model.getFilteredTransactionList());
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
