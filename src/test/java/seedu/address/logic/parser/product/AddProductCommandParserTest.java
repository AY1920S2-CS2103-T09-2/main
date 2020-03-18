package seedu.address.logic.parser.product;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_BAG;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_WATCH;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRICE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_QUANTITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SALES_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.PRICE_DESC_BAG;
import static seedu.address.logic.commands.CommandTestUtil.PRICE_DESC_WATCH;
import static seedu.address.logic.commands.CommandTestUtil.QUANTITY_DESC_BAG;
import static seedu.address.logic.commands.CommandTestUtil.QUANTITY_DESC_WATCH;
import static seedu.address.logic.commands.CommandTestUtil.SALES_DESC_BAG;
import static seedu.address.logic.commands.CommandTestUtil.SALES_DESC_WATCH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_WATCH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_WATCH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_WATCH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALES_WATCH;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalProducts.BAG;
import static seedu.address.testutil.TypicalProducts.WATCH;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.product.AddProductCommand;
import seedu.address.model.product.Description;
import seedu.address.model.product.Price;
import seedu.address.model.product.Product;
import seedu.address.model.product.Sales;
import seedu.address.model.util.Quantity;
import seedu.address.testutil.ProductBuilder;

public class AddProductCommandParserTest {
    private AddProductCommandParser parser = new AddProductCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Product expectedProduct = new ProductBuilder(WATCH).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + DESCRIPTION_DESC_WATCH + PRICE_DESC_WATCH + QUANTITY_DESC_WATCH
                + SALES_DESC_WATCH, new AddProductCommand(expectedProduct));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, DESCRIPTION_DESC_BAG + DESCRIPTION_DESC_WATCH
                + PRICE_DESC_WATCH + QUANTITY_DESC_WATCH
                + SALES_DESC_WATCH, new AddProductCommand(expectedProduct));

        // multiple prices - last price accepted
        assertParseSuccess(parser, DESCRIPTION_DESC_WATCH + PRICE_DESC_BAG + PRICE_DESC_WATCH + QUANTITY_DESC_WATCH
                + SALES_DESC_WATCH, new AddProductCommand(expectedProduct));

        // multiple quantities - last quantity accepted
        assertParseSuccess(parser, DESCRIPTION_DESC_WATCH + PRICE_DESC_WATCH + QUANTITY_DESC_BAG + QUANTITY_DESC_WATCH
                + SALES_DESC_WATCH, new AddProductCommand(expectedProduct));

        // multiple sales - last sales accepted
        assertParseSuccess(parser, DESCRIPTION_DESC_WATCH + PRICE_DESC_WATCH + QUANTITY_DESC_WATCH + SALES_DESC_BAG
                + SALES_DESC_WATCH, new AddProductCommand(expectedProduct));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Product expectedProduct = new ProductBuilder(BAG).build();
        assertParseSuccess(parser, DESCRIPTION_DESC_BAG + PRICE_DESC_BAG + QUANTITY_DESC_BAG + SALES_DESC_BAG,
                new AddProductCommand(expectedProduct));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProductCommand.MESSAGE_USAGE);

        // missing description prefix
        assertParseFailure(parser, VALID_DESCRIPTION_WATCH + PRICE_DESC_WATCH + QUANTITY_DESC_WATCH + SALES_DESC_WATCH,
                expectedMessage);

        // missing price prefix
        assertParseFailure(parser, DESCRIPTION_DESC_WATCH + VALID_PRICE_WATCH + QUANTITY_DESC_WATCH + SALES_DESC_WATCH,
                expectedMessage);

        // missing quantity prefix
        assertParseFailure(parser, DESCRIPTION_DESC_WATCH + PRICE_DESC_WATCH + VALID_QUANTITY_WATCH + SALES_DESC_WATCH,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_DESCRIPTION_WATCH
                        + VALID_PRICE_WATCH + VALID_QUANTITY_WATCH + VALID_SALES_WATCH,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_DESCRIPTION_DESC + PRICE_DESC_WATCH + QUANTITY_DESC_WATCH + SALES_DESC_WATCH,
               Description.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, DESCRIPTION_DESC_WATCH + INVALID_PRICE_DESC + QUANTITY_DESC_WATCH + SALES_DESC_WATCH,
                Price.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, DESCRIPTION_DESC_WATCH + PRICE_DESC_WATCH + INVALID_QUANTITY_DESC + SALES_DESC_WATCH,
                Quantity.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, DESCRIPTION_DESC_WATCH
                        + PRICE_DESC_WATCH + QUANTITY_DESC_WATCH + INVALID_SALES_DESC,
                Sales.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_DESCRIPTION_DESC
                        + PRICE_DESC_WATCH + QUANTITY_DESC_WATCH + INVALID_SALES_DESC,
                Description.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + DESCRIPTION_DESC_WATCH + PRICE_DESC_WATCH + QUANTITY_DESC_WATCH
                        + SALES_DESC_WATCH,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProductCommand.MESSAGE_USAGE));
    }
}
