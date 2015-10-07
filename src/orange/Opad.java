package orange;

import java.math.BigInteger;
import java.util.NavigableSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

/**
 * The Opad class extends the abstract class AbstractProducts.
 *
 * @author Andrew Hwang
 * @version 1.0 Sept 8, 2015
 */
public final class Opad extends AbstractProducts{

    /**
     * Constructor takes in the same parameters as the AbstractProducts Constructor.
     * @param serialNumber
     * @param description
     */
    Opad(SerialNumber serialNumber, Optional<Set<String>> description) {
        super(serialNumber, description);
    }

    /**
     * Overrides the the ProductType method from AbstractProducts.
     * @return the product type OPAD.
     */
    @Override
    public ProductType getProductType(){
        return ProductType.OPAD;
    }

    /**
     * An oPad is exchanged with the product that has the largest
     * compatible serial number that is strictly less than the original oPad
     * serial number. If no such compatible product exists, the exchange fails.
     * Otherwise, the status is set to OK and the result is set to the serial
     * number of the new oPad.
     * @param request
     * @param status
     * @throws ProductException
     */
    @Override
    public void process(Exchange request, RequestStatus status) throws ProductException {
        NavigableSet<SerialNumber> tempSet = new TreeSet<>(request.getCompatibleProducts());
        SerialNumber compatSerialNumber = tempSet.lower(serialNumber);

        if (compatSerialNumber.getSerialNumber().compareTo(BigInteger.ZERO) > 0){
            status.setStatusCode(RequestStatus.StatusCode.OK);
            status.setResult(Optional.of(compatSerialNumber.getSerialNumber()));
        }
        else {
            status.setStatusCode(RequestStatus.StatusCode.FAIL);
        }
    }

    /**
     * An oPad refund succeeds if and only if the greatest common divisor of
     * the RMA and the serial number is at least 12, in which case the status
     * is set to OK and the result is set to undefined.
     * @param request
     * @param status
     * @throws ProductException
     */
    @Override
    public void process(Refund request, RequestStatus status) throws ProductException {
        int rmaGcd = request.getRma().gcd(this.serialNumber.getSerialNumber()).compareTo(BigInteger.valueOf(12));

        if (0 <= rmaGcd){
            status.setStatusCode(RequestStatus.StatusCode.OK);
            status.setResult(Optional.<BigInteger>empty());
        }
        else
            status.setStatusCode(RequestStatus.StatusCode.FAIL);
    }

    /**
     * Checks if the oPad serial number is valid.
     * @param serialNumber
     * @return true if the number is even and the 3rd bit is set.
     */
    public static Boolean isValidSerialNumber(SerialNumber serialNumber){
        if (serialNumber.isEven() && serialNumber.testBit(2)){
            return true;
        }
        else
            return false;
    }
}
