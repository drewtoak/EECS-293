package orange;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Andrew Hwang on 9/16/2015.
 */
public class OtvTest {

    private Otv o1;
    private SerialNumber serialNumber1;
    private Optional<Set<String>> description1;

    @Before
    public void setUp() throws Exception {
        serialNumber1 = new SerialNumber(BigInteger.valueOf(1));

        Set<String> set1 = new HashSet<>();
        set1.add("This product was developed in Cleveland");
        set1.add("This is an oTv");
        set1.add("This is new");
        description1 = Optional.of(set1);

        o1 = new Otv(serialNumber1, description1);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetProductType() throws Exception {
        assertEquals(o1.getProductType(), ProductType.OTV);
    }

    @Test
    public void testIsValidSerialNumber() throws Exception {
        assertEquals(Otv.isValidSerialNumber(o1.getSerialNumber()), true);
    }
}