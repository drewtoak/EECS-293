package orange;

import java.math.BigInteger;
import java.util.Optional;
import java.util.Set;

/**
 * The Opod class extends the abstract class AbstractProducts.
 *
 * @author Andrew Hwang
 * @version 1.0 Sept 8, 2015
 */
public final class Opod extends AbstractProducts {

    /**
     * Constructor takes in the same parameters as the AbstractProducts Constructor.
     * @param serialNumber
     * @param description
     */
    Opod(SerialNumber serialNumber, Optional<Set<String>> description) {
        super(serialNumber, description);
    }

    /**
     * Overrides the the ProductType method from AbstractProducts.
     * @return the product type OPOD.
     */
    @Override
    public ProductType getProductType(){
        return ProductType.OPOD;
    }

    /**
     * An oPod is exchanged with any product that has a compatible
     * serial number. If no compatible product exists, the exchange fails.
     * Otherwise, the status is set to OK and the result is set to the serial
     * number of the new oPad.
     * @param request
     * @param status
     * @throws ProductException
     */
    @Override
    public void process(Exchange request, RequestStatus status) throws ProductException {
        SerialNumber compatibleNumber = new SerialNumber(BigInteger.ZERO);

        for (SerialNumber compatSerialNumber: request.getCompatibleProducts()) {
            if (serialNumber.compareTo(compatSerialNumber) == 0) {
                compatibleNumber = compatSerialNumber;
            }
        }

        if (compatibleNumber.getSerialNumber().compareTo(BigInteger.ZERO) > 0){
            status.setStatusCode(RequestStatus.StatusCode.OK);
            status.setResult(Optional.of(compatibleNumber.getSerialNumber()));
        }
        else {
            status.setStatusCode(RequestStatus.StatusCode.FAIL);
        }
    }

    /**
     * An oPod refund succeeds if and only if the greatest common divisor of
     * the RMA and the serial number is at least 24, in which case the status
     * is set to OK and the result is set to undefined.
     * @param request
     * @param status
     * @throws ProductException
     */
    @Override
    public void process(Refund request, RequestStatus status) throws ProductException {
        int rmaGcd = request.getRma().gcd(this.serialNumber.getSerialNumber()).compareTo(BigInteger.valueOf(24));

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
     * @return true if the number is even and the 3rd bit is not set.
     */
    public static Boolean isValidSerialNumber(SerialNumber serialNumber){
        if (serialNumber.isEven() && !serialNumber.testBit(2)){
            return  true;
        }
        else
            return false;
    }
}
