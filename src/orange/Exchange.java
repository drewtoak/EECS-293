package orange;


import java.util.*;

/**
 * Exchange is a class that implements the interface class Request.
 *
 * @author Andrew Hwang
 * @version 1.0 Sept. 21, 2015
 */
final class Exchange implements Request {

    /**
     * Fields:
     * NavigableSet field labeled compatibleSet.
     */
    NavigableSet<SerialNumber> compatibleSet;

    /**
     * Private constructor for Exchange.
     * Sets the compatibleSet variable as a TreeSet of the builder's compatible product serial numbers.
     * @param builder
     */
    private Exchange(Builder builder){
        this.compatibleSet = new TreeSet<>(builder.getCompatibleProducts());
    }

    /**
     * Overrides the process method from the Request class.
     * @param product
     * @param status
     * @throws RequestExceptions
     */
    @Override
    public void process(Product product, RequestStatus status) throws RequestExceptions {
        try{
            product.process(this, status);
        }
        catch (ProductException e) {
            throw new RequestExceptions("There was a request error", e);
        }
    }

    /**
     * Gets the compatible products' serial numbers as a set.
     * @return NavigableSet of Serial Numbers.
     */
    public NavigableSet<SerialNumber> getCompatibleProducts(){
        NavigableSet<SerialNumber> getSet = new TreeSet<>(this.compatibleSet);
        return getSet;
    }

    /**
     * Builder is a nested static class
     * that builds an Exchange.
     */
    public static class Builder {

        /**
         * Fields:
         * Set of SerialNumber field labeled set;
         */
        Set<SerialNumber> set;
        Set<SerialNumber> getSet;

        public Builder(){
            set = new TreeSet<>();
        }

        /**
         * Adds compatible serial numbers to the set within Builder.
         * @param serialNumber
         * @return a new Builder.
         */
        public Builder addCompatible(SerialNumber serialNumber){
            set.add(serialNumber);
            return this;
        }

        /**
         * Gets the set of compatible products' serial numbers.
         * @return a Set of SerialNumbers.
         */
        public Set<SerialNumber> getCompatibleProducts(){
            getSet = new TreeSet<>(set);
            return getSet;
        }

        /**
         * Builds the Exchange.
         * @return a new Exchange with the parameter of this Builder.
         */
        public Exchange build(){
            return new Exchange(this);
        }
    }

}
