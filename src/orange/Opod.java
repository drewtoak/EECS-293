package orange;

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
