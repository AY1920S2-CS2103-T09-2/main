package seedu.address.logic.parser.statistics;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import java.util.stream.Stream;

import seedu.address.logic.commands.statistics.RevenueCommand;
import seedu.address.logic.parser.*;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.statistics.StartEndDate;

/**
 * Parses input arguments and creates a new RevenueCommand object
 */
public class RevenueCommandParser implements Parser<RevenueCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RevenueCommand
     * and returns an RevenueCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RevenueCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_START_DATE, PREFIX_END_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_START_DATE, PREFIX_END_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RevenueCommand.MESSAGE_USAGE));
        }

        StartEndDate startDate = ParserUtil.parseStartDate(argMultimap.getValue(PREFIX_START_DATE).get());
        StartEndDate endDate = ParserUtil.parseEndDate(argMultimap.getValue(PREFIX_END_DATE).get());

        return new RevenueCommand(startDate, endDate);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}