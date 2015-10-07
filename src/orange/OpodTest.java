package orange;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by andrewhwang on 9/28/15.
 */
public class OpodTest extends TestCase {

    private Opod o1;
    private SerialNumber serialNumber1;
    private Optional<Set<String>> description1;
    private Exchange exchange;
    private RequestStatus status;
    private Refund refund;

    @Before
    public void setUp() throws Exception {
        serialNumber1 = new SerialNumber(BigInteger.valueOf(10000));

        Set<String> set1 = new HashSet<>();
        set1.add("This product was developed in Cleveland");
        set1.add("This is an oPod");
        set1.add("This is new");
        description1 = Optional.of(set1);

        o1 = new Opod(serialNumber1, description1);

        Exchange.Builder exchangeBuilder = new Exchange.Builder();
        SerialNumber s1 = new SerialNumber(BigInteger.valueOf(10000));
        SerialNumber s2 = new SerialNumber(BigInteger.valueOf(10002));

        exchangeBuilder.addCompatible(s1);
        exchangeBuilder.addCompatible(s2);

        exchange = exchangeBuilder.build();
        status = new RequestStatus();

        Refund.Builder refundBuilder = new Refund.Builder();
        BigInteger rma = BigInteger.valueOf(500);

        refundBuilder.setRma(rma);

        refund = refundBuilder.build();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetProductType() throws Exception {
        assertEquals(o1.getProductType(), ProductType.OPOD);
    }

    @Test
    public void testIsValidSerialNumber() throws Exception {
        assertTrue(Opod.isValidSerialNumber(o1.getSerialNumber()));
    }

    @Test
    public void testGetSerialNumber() throws Exception {
        assertEquals(o1.getSerialNumber(), serialNumber1);
    }

    @Test
    public void testGetProductName() throws Exception {
        assertEquals(o1.getProductName(), "oPod");
    }

    @Test
    public void testGetDescription() throws Exception {
        assertEquals(o1.getDescription(), description1);
    }

    @Test
    public void testEquals() throws Exception {
        Opod o2 = new Opod(o1.getSerialNumber(), o1.getDescription());
        Opod o3 = new Opod(new SerialNumber(BigInteger.valueOf(25)), o1.getDescription());
        assertEquals(o1.getSerialNumber().equals(o2.getSerialNumber()), true);
        assertEquals(o1.getSerialNumber().equals(o3.getSerialNumber()), false);
    }

    @Test
    public void testHashCode() throws Exception {
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(10000));
        Opod o2 = new Opod(serialNumber, null);
        assertEquals(o1.hashCode(), o2.hashCode());
    }

    @Test
    public void testToString() throws Exception {
        Opod o2 = new Opod(o1.getSerialNumber(), o1.getDescription());
        assertEquals(o1.toString(), o2.toString());
        System.out.print(o1.toString());
    }

    @Test
    public void testMake() throws ProductException {
        Opod o2 = new Opod(o1.getSerialNumber(), o1.getDescription());
        assertEquals(AbstractProducts.make(o1.getProductType(), o1.getSerialNumber(), o1.getDescription()).hashCode(),
                AbstractProducts.make(o2.getProductType(), o2.getSerialNumber(), o2.getDescription()).hashCode());
        System.out.print(AbstractProducts.make(o1.getProductType(), o1.getSerialNumber(), o1.getDescription()));
    }

    @Test
    public void testExchangeProcess() throws Exception {
        o1.process(exchange, status);
        assertEquals(status.getStatusCode(), RequestStatus.StatusCode.OK);
    }
    @Test
    public void testRefundProcess() throws Exception {
        o1.process(refund, status);
        assertEquals(status.getStatusCode(), RequestStatus.StatusCode.OK);
    }
}