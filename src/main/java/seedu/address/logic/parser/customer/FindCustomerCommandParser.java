package seedu.address.logic.parser.customer;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.logic.commands.customer.FindCustomerCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.customer.AddressContainsKeywordsPredicate;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.EmailContainsKeywordsPredicate;
import seedu.address.model.customer.JointCustomerPredicate;
import seedu.address.model.customer.NameContainsKeywordsPredicate;
import seedu.address.model.customer.PhoneContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCustomerCommand object
 */
public class FindCustomerCommandParser implements Parser<FindCustomerCommand> {
    private final List<Predicate<Customer>> predicates = new ArrayList<>();
    /**
     * Parses the given {@code String} of arguments in the context of the FindCustomerCommand
     * and returns a FindCustomerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCustomerCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        if (!anyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCustomerCommand.MESSAGE_USAGE));
        }

        if (anyPrefixesPresent(argMultimap, PREFIX_NAME)) {
            String customerArgs = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()).toString();
            String[] customerKeywords = customerArgs.split("\\s+");
            predicates.add(new NameContainsKeywordsPredicate(Arrays.asList(customerKeywords)));
        }
        if (anyPrefixesPresent(argMultimap, PREFIX_ADDRESS)) {
            String addressArgs = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()).toString();
            String[] addressKeywords = addressArgs.split("\\s+");
            predicates.add(new AddressContainsKeywordsPredicate(Arrays.asList(addressKeywords)));
        }
        if (anyPrefixesPresent(argMultimap, PREFIX_EMAIL)) {
            String emailArgs = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()).toString();
            String[] emailKeywords = emailArgs.split("\\s+");
            predicates.add(new EmailContainsKeywordsPredicate(Arrays.asList(emailKeywords)));
        }
        if (anyPrefixesPresent(argMultimap, PREFIX_PHONE)) {
            String phoneArgs = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()).toString();
            String[] phoneKeywords = phoneArgs.split("\\s+");
            predicates.add(new PhoneContainsKeywordsPredicate(Arrays.asList(phoneKeywords)));
        }

        return new FindCustomerCommand(new JointCustomerPredicate(predicates));
    }

    /**
     * Returns true if any of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
