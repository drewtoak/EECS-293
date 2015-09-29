package orange;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.NavigableSet;
import java.util.TreeSet;

import static org.junit.Assert.*;

/**
 * Created by Andrew Hwang on 9/23/2015.
 */
public class ExchangeTest {

    Exchange exchange;
    Exchange.Builder builder;
    SerialNumber s1;
    SerialNumber s2;

    @Before
    public void setUp() throws Exception {
        builder = new Exchange.Builder();
        s1 = new SerialNumber(BigInteger.valueOf(20));
        s2 = new SerialNumber(BigInteger.valueOf(21));

        builder.addCompatible(s1);
        builder.addCompatible(s2);

        exchange = builder.build();

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testProcess() throws Exception {

    }

    @Test
    public void testGetCompatibleProducts() throws Exception {
        NavigableSet<SerialNumber> compatibleSet = new TreeSet<>();
        compatibleSet.add(s1);
        compatibleSet.add(s2);
        assertEquals(exchange.getCompatibleProducts(), compatibleSet);
    }
}