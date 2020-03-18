package seedu.address.logic.commands.transaction;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT;
import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.transaction.Transaction;

public class FindTransactionCommand extends Command {
    public static final String COMMAND_WORD = "findt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all products whose descriptions contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers. "
            + "At least one field must be present in the command. \n"
            + "Parameters: "
            + "[" + PREFIX_CUSTOMER + "CUSTOMER] "
            + "[" + PREFIX_PRODUCT + "PRODUCT] "
            + "[" + PREFIX_DATETIME + "DATETIME] \n"
            + "Example: " + COMMAND_WORD
            + PREFIX_CUSTOMER + "Bob "
            + PREFIX_PRODUCT + "WaterMelon "
            + PREFIX_DATETIME + "2020-02-20 10:00 ";

    private final Predicate<Transaction> predicate;

    public FindTransactionCommand(Predicate<Transaction> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTransactionList(predicate);
        System.out.println("Find " + model.getFilteredTransactionList());
        return new CommandResult(
                String.format(Messages.MESSAGE_TRANSACTIONS_LISTED_OVERVIEW, model.getFilteredTransactionList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTransactionCommand // instanceof handles nulls
                && predicate.equals(((FindTransactionCommand) other).predicate)); // state check
    }
}
