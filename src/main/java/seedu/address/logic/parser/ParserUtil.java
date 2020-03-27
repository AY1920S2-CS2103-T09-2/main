package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.customer.Address;
import seedu.address.model.customer.Email;
import seedu.address.model.customer.Name;
import seedu.address.model.customer.Phone;
import seedu.address.model.product.CostPrice;
import seedu.address.model.product.Price;
import seedu.address.model.product.Sales;
import seedu.address.model.statistics.StartEndDate;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.DateTime;
import seedu.address.model.transaction.Money;
import seedu.address.model.util.Description;
import seedu.address.model.util.Quantity;
import seedu.address.model.util.QuantityThreshold;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Price parsePrice(String price) throws ParseException {
        requireNonNull(price);
        String trimmedPrice = price.trim();
        if (!Price.isValidPrice(trimmedPrice)) {
            throw new ParseException(Price.MESSAGE_CONSTRAINTS);
        }
        return new Price(trimmedPrice);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static CostPrice parseCostPrice(String price) throws ParseException {
        requireNonNull(price);
        String trimmedPrice = price.trim();
        if (!CostPrice.isValidPrice(trimmedPrice)) {
            throw new ParseException(Price.MESSAGE_CONSTRAINTS);
        }
        return new CostPrice(trimmedPrice);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Quantity parseQuantity(String quantity) throws ParseException {
        requireNonNull(quantity);
        String trimmedQuantity = quantity.trim();
        if (!Quantity.isValidQuantity(trimmedQuantity)) {
            throw new ParseException(Quantity.MESSAGE_CONSTRAINTS);
        }
        return new Quantity(trimmedQuantity);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Sales parseSales(String sales) throws ParseException {
        requireNonNull(sales);
        String trimmedSales = sales.trim();
        if (!Sales.isValidSales(trimmedSales)) {
            throw new ParseException(Sales.MESSAGE_CONSTRAINTS);
        }
        return new Sales(trimmedSales);
    }

    /**
     * Parses a {@code String customer} into an {@code Customer}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code customer} is invalid.
     */
    public static String parseCustomer(String customer) throws ParseException {
        requireNonNull(customer);
        String trimmedCustomer = customer.trim();
        //        if (!Sales.isValidSales(trimmedSales)) {
        //            throw new ParseException(Quantity.MESSAGE_CONSTRAINTS);
        //        }
        //        return new Sales(trimmedSales);
        return trimmedCustomer;
    }

    /**
     * Parses a {@code String product} into an {@code product}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code product} is invalid.
     */
    public static String parseProduct(String product) throws ParseException {
        requireNonNull(product);
        String trimmedProduct = product.trim();
        //        if (!Sales.isValidSales(trimmedSales)) {
        //            throw new ParseException(Quantity.MESSAGE_CONSTRAINTS);
        //        }
        //        return new Sales(trimmedSales);
        return trimmedProduct;
    }

    /**
     * Parses a {@code String product} into an {@code product}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code product} is invalid.
     */
    public static DateTime parseDateTime(String dateTime) throws ParseException {
        requireNonNull(dateTime);
        String trimmedDateTime = dateTime.trim();
        if (!DateTime.isValidDateTime(trimmedDateTime)) {
            throw new ParseException(DateTime.MESSAGE_CONSTRAINTS);
        }
        return new DateTime(trimmedDateTime);
    }

    /**
     * Parses a {@code String product} into an {@code product}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code product} is invalid.
     */
    public static Money parseMoney(String money) throws ParseException {
        requireNonNull(money);
        String trimmedMoney = money.trim();
        if (!Money.isValidMoney(trimmedMoney)) {
            throw new ParseException(Money.MESSAGE_CONSTRAINTS);
        }
        return new Money(trimmedMoney);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Description parseTransDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String startDate} into an {@code StartEndDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code startDate} is invalid.
     */
    public static StartEndDate parseStartDate(String startDate) throws ParseException {
        requireNonNull(startDate);
        String trimmedStartEndDate = startDate.trim();
        if (!StartEndDate.isValidStartEndDate(trimmedStartEndDate)) {
            throw new ParseException(StartEndDate.MESSAGE_CONSTRAINTS);
        }
        return new StartEndDate(startDate);
    }

    /**
     * Parses a {@code String endDate} into an {@code StartEndDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code endDate} is invalid.
     */
    public static StartEndDate parseEndDate(String endDate) throws ParseException {
        requireNonNull(endDate);
        String trimmedStartEndDate = endDate.trim();
        if (!StartEndDate.isValidStartEndDate(trimmedStartEndDate)) {
            throw new ParseException(StartEndDate.MESSAGE_CONSTRAINTS);
        }
        return new StartEndDate(endDate);
    }

    /**
     * Parses a {@code String threshold} into an {@code trimmedThreshold}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static QuantityThreshold parseThreshold(String threshold) throws ParseException {
        requireNonNull(threshold);
        String trimmedThreshold = threshold.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedThreshold)) {
            throw new ParseException(QuantityThreshold.MESSAGE_CONSTRAINTS);
        }
        return new QuantityThreshold(trimmedThreshold);
    }
}
