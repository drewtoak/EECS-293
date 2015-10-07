package orange;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

/**
 * Created by Andrew Hwang on 9/16/2015.
 */
public class OphoneTest extends TestCase{

    private Ophone o1;
    private Exchange exchange;
    private RequestStatus status;
    private Refund refund;

    @Before
    public void setUp() throws Exception {
        SerialNumber serialNumber1 = new SerialNumber(BigInteger.valueOf(63));

        Set<String> set1 = new HashSet<>();
        set1.add("This product was developed in Cleveland");
        set1.add("This is an oPhone");
        set1.add("This is new");
        Optional<Set<String>> description1 = Optional.of(set1);

        o1 = new Ophone(serialNumber1, description1);

        Exchange.Builder exchangeBuilder = new Exchange.Builder();
        SerialNumber s1 = new SerialNumber(BigInteger.valueOf(220));
        SerialNumber s2 = new SerialNumber(BigInteger.valueOf(1032));
        SerialNumber s3 = new SerialNumber(BigInteger.valueOf(1244));

        exchangeBuilder.addCompatible(s1);
        exchangeBuilder.addCompatible(s2);
        exchangeBuilder.addCompatible(s3);

        exchange = exchangeBuilder.build();
        status = new RequestStatus();

        Refund.Builder refundBuilder = new Refund.Builder();
        BigInteger rma = BigInteger.valueOf(126);

        refundBuilder.setRma(rma);

        refund = refundBuilder.build();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetProductType() throws Exception {
        assertEquals(o1.getProductType(), ProductType.OPHONE);
    }

    @Test
    public void testIsValidSerialNumber() throws Exception {
        assertTrue(o1.isValidSerialNumber(o1.getSerialNumber()));
    }

    @Test
    public void testExchangeProcess() throws Exception {
        o1.process(exchange, status);
        assertEquals(status.getStatusCode(), RequestStatus.StatusCode.OK);
    }

    @Test
    public void testRefundProcess() throws Exception {
        o1.process(refund, status);
        assertEquals(status.getStatusCode(), RequestStatus.StatusCode.FAIL);
    }
}