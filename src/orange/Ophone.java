package orange;

import java.math.BigInteger;
import java.util.NavigableSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

/**
 * The Ophone class extends the abstract class AbstractProducts.
 *
 * @author Andrew Hwang
 * @version 1.1 Sept 16, 2015
 */
public final class Ophone extends AbstractProducts{

    /**
     * Constructor takes in the same parameters as the AbstractProducts Constructor.
     * @param serialNumber
     * @param description
     */
    Ophone(SerialNumber serialNumber, Optional<Set<String>> description) {
        super(serialNumber, description);

    }

    /**
     * Overrides the the ProductType method from AbstractProducts.
     * @return the product type OPHONE.
     */
    @Override
    public ProductType getProductType(){
        return ProductType.OPHONE;
    }

    /**
     * An oPhone exchange works as follows. Consider the compatible
     * serial numbers that are strictly greater than the oPhone’s, and take their
     * average. Then, exchange the oPhone with the largest compatible serial
     * number that is strictly greater than the oPhone’s and strictly less than
     * the average. If no such compatible product exists, then the exchange fails.
     * @param request
     * @param status
     * @throws ProductException
     */
    @Override
    public void process(Exchange request, RequestStatus status) throws ProductException {
        BigInteger sum = BigInteger.ZERO;
        NavigableSet<SerialNumber> tempSet = new TreeSet<>(request.getCompatibleProducts());
        NavigableSet<SerialNumber> largerThanOphone = tempSet.tailSet(serialNumber,false);

        for (SerialNumber compatSerialNumber: request.getCompatibleProducts()){
            if (serialNumber.compareTo(compatSerialNumber) < 0){
                sum = sum.add(compatSerialNumber.getSerialNumber());
            }
        }

        SerialNumber avg = new SerialNumber(sum.divide(BigInteger.valueOf(request.getCompatibleProducts().size())));
        NavigableSet<SerialNumber> lessThanAvg = largerThanOphone.headSet(avg, false);

        if (!lessThanAvg.isEmpty()) {
            SerialNumber largest = lessThanAvg.last();
            status.setStatusCode(RequestStatus.StatusCode.OK);
            status.setResult(Optional.of(largest.getSerialNumber()));
        }
        else {
            status.setStatusCode(RequestStatus.StatusCode.FAIL);
        }
    }

    /**
     * An oPhone succeeds if and only if the serial number can be obtained
     * by shifting to the left the RMA by 1, 2, or 3 bits, in which case the status
     * is set to OK and the result is set to undefined.
     * @param request
     * @param status
     * @throws ProductException
     */
    @Override
    public void process(Refund request, RequestStatus status) throws ProductException {
        BigInteger rma = request.getRma();

        byte rmaByte1 = rma.byteValue();
        byte rmaByte2 = rma.byteValue();
        byte rmaByte3 = rma.byteValue();

        SerialNumber shiftedOne = new SerialNumber(BigInteger.valueOf(rmaByte1 << 1));
        SerialNumber shiftedTwo = new SerialNumber(BigInteger.valueOf(rmaByte2 << 2));
        SerialNumber shiftedThree = new SerialNumber(BigInteger.valueOf(rmaByte3 << 3));

        if (shiftedOne.getSerialNumber().compareTo(serialNumber.getSerialNumber()) == 0){
            status.setStatusCode(RequestStatus.StatusCode.OK);
            status.setResult(Optional.<BigInteger>empty());
        }
        else if (shiftedTwo.getSerialNumber().compareTo(serialNumber.getSerialNumber()) == 0){
            status.setStatusCode(RequestStatus.StatusCode.OK);
            status.setResult(Optional.<BigInteger>empty());
        }
        else if (shiftedThree.getSerialNumber().compareTo(serialNumber.getSerialNumber()) == 0){
            status.setStatusCode(RequestStatus.StatusCode.OK);
            status.setResult(Optional.<BigInteger>empty());
        }
        else
            status.setStatusCode(RequestStatus.StatusCode.FAIL);
    }

    /**
     * Checks if the oPad serial number is valid.
     * @param serialNumber
     * @return true if the number is odd and gcd(630) > 42.
     */
    public static Boolean isValidSerialNumber(SerialNumber serialNumber){
        SerialNumber other = new SerialNumber(BigInteger.valueOf(630));
        if (serialNumber.isOdd() && Ophone.gcdOfTwoSerialNumbers(serialNumber, other)){
            return true;
        }
        else
            return false;
    }

    /**
     * Helper static method that calculates the gcd of two serial numbers
     * checks if the serial number is greater than 42.
     * @param s1
     * @param s2
     * @return true if conditions are met.
     */
    public static boolean gcdOfTwoSerialNumbers(SerialNumber s1, SerialNumber s2){
        BigInteger fTwo = BigInteger.valueOf(42);
        int greaterThanFTwo = s1.getSerialNumber().gcd(s2.getSerialNumber()).compareTo(fTwo);
        return (0 < greaterThanFTwo);
    }
}
