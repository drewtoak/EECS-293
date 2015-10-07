package orange;

import java.math.BigInteger;
import java.util.Optional;

/**
 * RequestStatus class has a nested enum class StatusCode and has getters and setters.
 *
 * @author Andrew Hwang
 * @version 1.0 Sept. 20, 2015
 */
public class RequestStatus {

    /**
     * Enum class with status codes.
     */
    public enum StatusCode {
        UKNOWN, OK, FAIL;
    }

    /**
     * Fields:
     * StatusCode field labeled statusCode.
     * Optional field of BigInteger labeled result.
     */
    private StatusCode statusCode;
    private Optional<BigInteger> result;

    /**
     * Constructor that sets the statusCode to be UNKOWN as default
     * sets result as empty.
     */
    public RequestStatus(){
        this.statusCode = StatusCode.UKNOWN;
        this.result = Optional.empty();
    }

    /**
     * Gets the StatusCode
     * @return statusCode of the current RequestStatus.
     */
    public StatusCode getStatusCode(){
        return this.statusCode;
    }

    /**
     * Sets the StatusCode for statusCode.
     * @param sc
     */
    public void setStatusCode(StatusCode sc){
        this.statusCode = sc;
    }

    /**
     * Gets the result.
     * @return the BigInteger of result.
     */
    public Optional<BigInteger> getResult(){
        return this.result;
    }

    /**
     * Sets the value of result
     * @param r
     */
    public void setResult(Optional<BigInteger> r){
        this.result = r;
    }
}
