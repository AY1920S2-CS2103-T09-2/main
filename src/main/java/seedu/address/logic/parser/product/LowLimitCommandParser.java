package seedu.address.logic.parser.product;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_THRESHOLD;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.product.LowLimitCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.util.QuantityThreshold;

/**
 * Parses input arguments and creates a new LowLimitCommand object.
 */
public class LowLimitCommandParser implements Parser<LowLimitCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the LowLimitCommand
     * and returns a LowLimitCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LowLimitCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PRODUCT, PREFIX_THRESHOLD);

        if (!anyPrefixesPresent(argMultimap, PREFIX_PRODUCT, PREFIX_THRESHOLD)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LowLimitCommand.MESSAGE_USAGE));
        }

        Index productIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_PRODUCT).get());
        QuantityThreshold threshold = ParserUtil.parseThreshold(argMultimap.getValue(PREFIX_THRESHOLD).get());

        return new LowLimitCommand(productIndex, threshold);
    }

    /**
     * Returns true if any of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
