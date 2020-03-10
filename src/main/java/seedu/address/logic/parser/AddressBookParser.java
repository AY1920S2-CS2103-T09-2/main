package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddProductCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;

import seedu.address.logic.commands.customercommands.AddCustomerCommand;
import seedu.address.logic.commands.customercommands.ClearCustomerCommand;
import seedu.address.logic.commands.customercommands.DeleteCustomerCommand;
import seedu.address.logic.commands.customercommands.EditCustomerCommand;
import seedu.address.logic.commands.customercommands.FindCustomerCommand;
import seedu.address.logic.commands.customercommands.ListCustomerCommand;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCustomerCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCustomerCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCustomerCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCustomerCommand.COMMAND_WORD:
            return new ClearCustomerCommand();

        case FindCustomerCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCustomerCommand.COMMAND_WORD:
            return new ListCustomerCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddProductCommand.COMMAND_WORD:
            return new AddProductCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}