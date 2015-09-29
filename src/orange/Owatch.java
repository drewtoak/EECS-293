package orange;

import java.math.BigInteger;
import java.util.Optional;
import java.util.Set;

/**
 * The Owatch class extends the abstract class AbstractProducts.
 *
 * @author Andrew Hwang
 * @version 1.1 Sept 16, 2015
 */
public final class Owatch extends AbstractProducts{

    /**
     * Constructor takes in the same parameters as the AbstractProducts Constructor.
     * @param serialNumber
     * @param description
     */
    Owatch(SerialNumber serialNumber, Optional<Set<String>> description) {
        super(serialNumber, description);
    }

    /**
     * Overrides the the ProductType method from AbstractProducts.
     * @return the product type OWATCH.
     */
    @Override
    public ProductType getProductType(){
        return ProductType.OWATCH;
    }

    @Override
    public void process(Exchange request, RequestStatus status) throws ProductException {
        throw new ProductException(this.getProductType(), this.getSerialNumber(),
                ProductException.ErrorCode.UNSUPPORTED_OPERATION);
    }

    @Override
    public void process(Refund request, RequestStatus status) throws ProductException {
        throw new ProductException(this.getProductType(), this.getSerialNumber(),
                ProductException.ErrorCode.UNSUPPORTED_OPERATION);
    }

    /**
     * Checks if the oPad serial number is valid.
     * @param serialNumber
     * @return true if the number is odd and 14 < gcd(630) <= 42.
     */
    public static Boolean isValidSerialNumber(SerialNumber serialNumber){
        SerialNumber other = new SerialNumber(BigInteger.valueOf(630));
        if (serialNumber.isOdd() && Owatch.gcdOfTwoSerialNumbers(serialNumber, other)){
            return  true;
        }
        else
            return false;
    }

    /**
     * Helper static method that calculates the gcd of two serial numbers
     * checks if the serial number is greater than 14 and less than or equal to 42.
     * @param s1
     * @param s2
     * @return true if conditions are met.
     */
    public static boolean gcdOfTwoSerialNumbers(SerialNumber s1, SerialNumber s2){
        BigInteger fTeen = BigInteger.valueOf(14);
        BigInteger fTwo = BigInteger.valueOf(42);
        int greaterThanFTeen = s1.getSerialNumber().gcd(s2.getSerialNumber()).compareTo(fTeen);
        int lessThanFTwo = s1.getSerialNumber().gcd(s2.getSerialNumber()).compareTo(fTwo);
        return ((0 < greaterThanFTeen) && (lessThanFTwo <= 0));
    }
}
