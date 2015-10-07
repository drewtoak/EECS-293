package orange;

import java.math.BigInteger;
import java.util.NavigableSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

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

    /**
     * As for exchanges, consider the compatible serial numbers that are
     * strictly greater than the oTv’s and less than or equal than the oTv’s plus
     * 1024. Then, take their average. Then, exchange the oTv with the largest
     * compatible serial number that is strictly greater than the oPhone’s and
     * strictly less than the average. If no such compatible product exists, then
     * the exchange fails.
     * @param request
     * @param status
     * @throws ProductException
     */
    @Override
    public void process(Exchange request, RequestStatus status) throws ProductException {
        NavigableSet<SerialNumber> tempSet = new TreeSet<>(request.getCompatibleProducts());
        BigInteger sum = BigInteger.ZERO;
        BigInteger add1024 = serialNumber.getSerialNumber();
        SerialNumber total = new SerialNumber(add1024.add(BigInteger.valueOf(1024)));

        for (SerialNumber compatSerialNumber: tempSet){
            if (serialNumber.compareTo(compatSerialNumber) < 0){
                sum = sum.add(compatSerialNumber.getSerialNumber());
            }
        }

        SerialNumber avg = new SerialNumber(sum.divide(BigInteger.valueOf(tempSet.size())));
        NavigableSet<SerialNumber> compatSerialNumbers =
                tempSet.headSet(total, true).
                tailSet(serialNumber,false).
                headSet(avg, false);

        if (!compatSerialNumbers.isEmpty()) {
            SerialNumber largest = compatSerialNumbers.last();
            status.setStatusCode(RequestStatus.StatusCode.OK);
            status.setResult(Optional.of(largest.getSerialNumber()));
        }
        else {
            status.setStatusCode(RequestStatus.StatusCode.FAIL);
        }

    }

    /**
     * A refund succeeds only if the RMA is positive, in which case the status
     * is set to OK and the result is set to undefined.
     * @param request
     * @param status
     * @throws ProductException
     */
    @Override
    public void process(Refund request, RequestStatus status) throws ProductException {
        if (!(request.getRma().compareTo(BigInteger.ZERO) <= 0)){
            status.setStatusCode(RequestStatus.StatusCode.OK);
            status.setResult(Optional.<BigInteger>empty());
        }
        else
            status.setStatusCode(RequestStatus.StatusCode.FAIL);
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
