package orange;

/**
 * Request is an Interface class
 *
 * @author Andrew Hwang
 * @version 1.0 Sept. 20, 2015.
 */
public interface Request {

    /**
     * Takes in product and status.
     * @param product
     * @param status
     * @throws RequestExceptions
     */
    public void process(Product product, RequestStatus status) throws RequestExceptions;
}
