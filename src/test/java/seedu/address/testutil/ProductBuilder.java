package seedu.address.testutil;

import seedu.address.model.product.Price;
import seedu.address.model.product.Product;
import seedu.address.model.product.Sales;
import seedu.address.model.util.Description;
import seedu.address.model.util.Quantity;

/**
 * A utility class to help with building Customer objects.
 */
public class ProductBuilder {

    public static final String DEFAULT_DESCRIPTION = "Black watch";
    public static final String DEFAULT_PRICE = "22";
    public static final String DEFAULT_QUANTITY = "12";
    public static final String DEFAULT_SALES = "44";

    private Description description;
    private Price price;
    private Sales sales;
    private Quantity quantity;

    public ProductBuilder() {
        description = new Description(DEFAULT_DESCRIPTION);
        price = new Price(DEFAULT_PRICE);
        quantity = new Quantity(DEFAULT_QUANTITY);
        sales = new Sales(DEFAULT_SALES);
    }

    /**
     * Initializes the ProductBuilder with the data of {@code productToCopy}.
     */
    public ProductBuilder(Product productToCopy) {
        description = productToCopy.getDescription();
        price = productToCopy.getPrice();
        quantity = productToCopy.getQuantity();
        sales = productToCopy.getSales();
    }

    /**
     * Sets the {@code Description} of the {@code Product} that we are building.
     */
    public ProductBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code Product} that we are building.
     */
    public ProductBuilder withPrice(String price) {
        this.price = new Price(price);
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code Product} that we are building.
     */
    public ProductBuilder withQuantity(String quantity) {
        this.quantity = new Quantity(quantity);
        return this;
    }

    /**
     * Sets the {@code Sales} of the {@code Product} that we are building.
     */
    public ProductBuilder withSales(String sales) {
        this.sales = new Sales(sales);
        return this;
    }

    public Product build() {
        return new Product(description, price, quantity, sales);
    }

}

