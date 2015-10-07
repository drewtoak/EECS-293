package orange;

import java.math.BigInteger;
import java.util.Optional;

/**
 * Refund is a final class that implements Request.
 *
 * @author Andrew Hwang
 * @version 1.0 Sept. 23, 2015.
 */
final class Refund implements Request {

    /**
     * Fields:
     * BigInteger labeled as rma.
     */
    BigInteger rma;

    /**
     * Overrides the method process from the Interface Request.
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
     * Private constructor that takes in a Builder
     * and sets the rma of Refund to the rma of the inputted builder.
     * @param builder
     */
    private Refund(Builder builder) {
        rma = builder.getRma();
    }

    /**
     * Gets the rma value of Refund.
     * @return rma
     */
    public BigInteger getRma() {
        BigInteger getRma = rma;
        return getRma;
    }

    /**
     * Builder is a nested static class.
     */
    public static class Builder{

        /**
         * Fields:
         * BigInteger field labeled rma.
         */
        BigInteger rma;

        public Builder(){
            rma = BigInteger.ZERO;
        }
        /**
         * Sets the rma value of Builder to the rma value inputted.
         * @param rma
         * @return returns a new Builder.
         */
        public Builder setRma(BigInteger rma) throws RequestExceptions{
            if (rma != null) {
                this.rma = rma;
                return this;
            }
            else
                throw new RequestExceptions(RequestStatus.StatusCode.FAIL, null,
                        RequestExceptions.RequestError.INVALID_RESULT);
        }

        /**
         * Gets the rma value of Builder.
         * @return rma
         */
        public BigInteger getRma(){
            return this.rma;
        }

        /**
         * Builds a Refund from this Builder
         * @return a new Refund with the rma value of this Builder.
         */
        public Refund build(){
            return new Refund(this);
        }
    }
}
