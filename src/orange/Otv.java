package orange;

import java.math.BigInteger;
import java.util.Optional;
import java.util.Set;

/**
 * The Otv class extends the abstract class AbstractProducts.
 *
 * @author Andrew Hwang
 * @version 1.1 Sept 16, 2015
 */
public final class Otv extends AbstractProducts{

    /**
     * Constructor takes in the same parameters as the AbstractProducts Constructor.
     * @param serialNumber
     * @param description
     */
    Otv(SerialNumber serialNumber, Optional<Set<String>> description) {
        super(serialNumber, description);
    }

    /**
     * Overrides the the ProductType method from AbstractProducts.
     * @return the product type OTV.
     */
    @Override
    public ProductType getProductType(){
        return ProductType.OTV;
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
     * @return true if the number is odd and gcd(630) <= 14.
     */
    public static Boolean isValidSerialNumber(SerialNumber serialNumber){
        SerialNumber other = new SerialNumber(BigInteger.valueOf(630));
        if (serialNumber.isOdd() && (Otv.gcdOfTwoSerialNumbers(serialNumber, other))){
            return true;
        }
        else
            return false;
    }

    /**
     * Helper static method that calculates the gcd of two serial numbers
     * checks if the serial number is less than 14
     * @param s1
     * @param s2
     * @return true if conditions are met
     */
    public static boolean gcdOfTwoSerialNumbers(SerialNumber s1, SerialNumber s2){
        BigInteger fTeen = BigInteger.valueOf(14);
        int lessThanFTeen = s1.getSerialNumber().gcd(s2.getSerialNumber()).compareTo(fTeen);
        return (lessThanFTeen <= 0);
    }
}
