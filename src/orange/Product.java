package orange;

import java.util.*;

/**
 * The Product class is an interface with predefined methods.
 *
 * @author Andrew Hwang
 * @version 1.1 Sept 23, 2015
 */
public interface Product {

    public SerialNumber getSerialNumber();

    public ProductType getProductType();

    public String getProductName();

    public Optional<Set<String>> getDescription();

    public void process(Exchange request, RequestStatus status) throws ProductException;

    public void process(Refund request, RequestStatus status) throws ProductException;
}
