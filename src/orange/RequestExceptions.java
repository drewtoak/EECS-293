package orange;


import java.math.BigInteger;
import java.util.Optional;

/**
 *
 * @author Andrew Hwang
 * @version 1.0 Sept. 20, 2015.
 */
public class RequestExceptions extends Exception{

    public enum RequestError{
        INVALID_STATUS_CODE, INVALID_RESULT;
    }

    RequestStatus.StatusCode statusCode;
    Optional<BigInteger> result;
    RequestError requestError;

    public RequestExceptions(RequestStatus.StatusCode statusCode,
                             Optional<BigInteger> result,
                             RequestError requestError){
        this.statusCode = statusCode;
        this.result = result;
        this.requestError = requestError;
    }

    public RequestExceptions(String msg, Exception cause){

    }

    public RequestStatus.StatusCode getStatusCode(){
        return this.statusCode;
    }

    public Optional<BigInteger> getResult(){
        return this.result;
    }

    public RequestError getRequestError(){
        return this.requestError;
    }
}
