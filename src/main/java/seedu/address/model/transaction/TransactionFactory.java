package seedu.address.model.transaction;

import java.util.UUID;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.customer.Customer;
import seedu.address.model.product.Product;
import seedu.address.model.util.Description;
import seedu.address.model.util.Money;
import seedu.address.model.util.Quantity;

/**
 * Creates a transaction with the customer and product index.
 */
public class TransactionFactory {
    private final Index customerIndex;
    private final Index productIndex;
    private final DateTime dateTime;
    private final Quantity quantity;
    private final Money money;
    private final Description description;

    public TransactionFactory(Index customerIndex, Index productIndex, DateTime dateTime,
                              Quantity quantity, Money money, Description description) {
        this.customerIndex = customerIndex;
        this.productIndex = productIndex;
        this.quantity = quantity;
        this.money = money;
        this.dateTime = dateTime;
        this.description = description;
    }

    /**
     * Creates a transaction with the found product and customer.
     * @param model the model manager.
     * @return created transaction.
     */
    public Transaction createTransaction(Model model) {
        Customer customer = model.getFilteredCustomerList().get(customerIndex.getZeroBased());
        Product product = model.getFilteredProductList().get(productIndex.getZeroBased());
        UUID productId = model.getFilteredProductList().get(productIndex.getZeroBased()).getId();

        return new Transaction(customer, product, productId, dateTime, quantity, money, description);
    }

    public Index getProductIndex() {
        return productIndex;
    }

    public Index getCustomerIndex() {
        return customerIndex;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public Money getMoney() {
        return money;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TransactionFactory)) {
            return false;
        }

        TransactionFactory otherTransactionFactory = (TransactionFactory) other;
        return otherTransactionFactory.customerIndex.equals(customerIndex)
                && otherTransactionFactory.productIndex.equals(productIndex)
                && otherTransactionFactory.dateTime.equals(dateTime);
    }
}
