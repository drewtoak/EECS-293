package orange;

import java.util.*;
import java.util.stream.Stream;

/**
 * The AbstractProducts class is an abstract class that implements the interface class Product.
 *
 * @author Andrew Hwang
 * @version 1.1 Sept 16, 2015
 */
public abstract class AbstractProducts implements Product {

    /**
     * Fields:
     * SerialNumber field labeled serialNumber.
     * Optional field labeled description that is a generic type Set of Strings.
     * ProductType field labeled productType.
     */
    SerialNumber serialNumber;
    Optional<Set<String>> description;
    /**
     *Constructor that takes in two parameters.
     * @param serialNumber
     * @param description
     */
    public AbstractProducts(SerialNumber serialNumber, Optional<Set<String>> description){
        this.serialNumber = serialNumber;
        this.description = description;
    }

    /**
     * Uses switch that takes in a producttype and returns a product for each case
     * except if the serial number is invalid then it returns an exception
     * returns another exception for a producttype that does not exist.
     * @param productType
     * @param serialNumber
     * @param description
     * @return product or exception
     * @throws ProductException
     */
    public static Product make(ProductType productType, SerialNumber serialNumber,
                               Optional<Set<String>> description) throws ProductException{
        Product product;
        switch (productType){
            case OPOD: product = new Opod(serialNumber, description);
                if (!Opod.isValidSerialNumber(serialNumber))
                    throw new ProductException(productType, serialNumber,
                            ProductException.ErrorCode.INVALID_SERIAL_NUMBER);
                else
                    return product;
            case OPAD: product = new Opad(serialNumber, description);
                if (!Opad.isValidSerialNumber(serialNumber))
                    throw new ProductException(productType, serialNumber,
                            ProductException.ErrorCode.INVALID_SERIAL_NUMBER);
                else
                    return product;
            case OPHONE: product = new Ophone(serialNumber, description);
                if (!Ophone.isValidSerialNumber(serialNumber))
                    throw new ProductException(productType, serialNumber,
                            ProductException.ErrorCode.INVALID_SERIAL_NUMBER);
                else
                    return product;
            case OWATCH: product = new Owatch(serialNumber, description);
                if (!Owatch.isValidSerialNumber(serialNumber))
                    throw new ProductException(productType, serialNumber,
                            ProductException.ErrorCode.INVALID_SERIAL_NUMBER);
                else
                    return product;
            case OTV: product = new Otv(serialNumber, description);
                if (!Otv.isValidSerialNumber(serialNumber))
                    throw new ProductException(productType, serialNumber,
                            ProductException.ErrorCode.INVALID_SERIAL_NUMBER);
                else
                    return product;
            default:
                throw new ProductException(productType, serialNumber,
                        ProductException.ErrorCode.INVALID_PRODUCT_TYPE);
        }
    }

    /**
     * Gets the serial number.
     * @return type SerialNumber.
     */
    public SerialNumber getSerialNumber(){
        return this.serialNumber;
    }


    /**
     * Gets the product name.
     * @return the String of the product name.
     */
    public String getProductName(){
        return this.getProductType().getName();
    }

    /**
     * Gets the description.
     * @return a Set of Strings.
     */
    public Optional<Set<String>> getDescription(){
        return this.description;
    }

    /**
     * Overrides the equals method.
     * Compares the hash codes of two serial numbers.
     * @param obj
     * @return true if the hash codes match or false if they do not.
     */
    @Override
    public boolean equals(Object obj){
        if (this.getSerialNumber().getClass() == obj.getClass()) {
            return this.getSerialNumber().hashCode() == obj.hashCode();
        }
        else
            return false;
    }

    /**
     * Overrides the hashCode method.
     * Gets the hash code of a serial number.
     * @return int value of the hash code.
     */
    @Override
    public int hashCode(){
        return this.getSerialNumber().getSerialNumber().hashCode();
    }

    /**
     * Overrides the toString method.
     * Converts a serial number's product name, serial number, and description to a String.
     * @return all of the aspects as a string.
     */
    @Override
    public String toString(){
        final StringBuilder build = new StringBuilder();

        build.append("Product Name: ");
        build.append(this.getProductName());
        build.append("\n");
        build.append("Serial Number: ");
        build.append(this.getSerialNumber().getSerialNumber());
        build.append("\n");
        build.append("Description: ");
        build.append("\n");

        /**
         * Using stream we build a stream of the description String Set.
         * Then every String value's first character is capitalized.
         * Then the whole String is appended to the String builder.
         * This happens for every String value in the set.
         * Appends an exception if there is no description.
         */
        if (this.getDescription().isPresent()){
            Stream<String> stream = this.getDescription().get().stream();
            stream.forEach(f -> {
                build.append(f.substring(0, 1).toUpperCase());
                build.append(f.substring(1, f.length()));
                build.append("\n");
            });
        }
        else
            build.append("There is no Description.");

        return build.toString();
    }
}
