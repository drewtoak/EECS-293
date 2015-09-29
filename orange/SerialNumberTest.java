package orange;

import static org.junit.Assert.*;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

public class SerialNumberTest {

    private SerialNumber s1;
    private SerialNumber s2;
    private SerialNumber s3;
    private SerialNumber s4;
    private SerialNumber s5;

    @Before
    public void setUp() throws Exception {
        s1 = new SerialNumber(BigInteger.valueOf(10000));
        s2 = new SerialNumber(BigInteger.valueOf(5002));
        s3 = new SerialNumber(BigInteger.valueOf(6001));
        s4 = new SerialNumber(BigInteger.valueOf(1311));
        s5 = new SerialNumber(BigInteger.valueOf(2021));

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetSerialNumber() throws Exception {
        assertEquals(s1.getSerialNumber().intValue(), 10000);
        assertEquals(s2.getSerialNumber().intValue(), 5002);
        assertEquals(s3.getSerialNumber().intValue(), 6001);
        assertEquals(s4.getSerialNumber().intValue(), 1311);
        assertEquals(s5.getSerialNumber().intValue(), 2021);

    }

    @Test
    public void testGcd() throws Exception {
        assertEquals(s1.gcd(new SerialNumber(BigInteger.valueOf(100))).intValue(), 100);
        assertEquals(s2.gcd(new SerialNumber(BigInteger.valueOf(100))).intValue(), 2);
        assertEquals(s3.gcd(new SerialNumber(BigInteger.valueOf(100))).intValue(), 1);
        assertEquals(s4.gcd(new SerialNumber(BigInteger.valueOf(100))).intValue(), 1);
        assertEquals(s5.gcd(new SerialNumber(BigInteger.valueOf(100))).intValue(), 1);
    }

    @Test
    public void testMod() throws Exception {
        assertEquals(s1.mod(new SerialNumber(BigInteger.valueOf(2))).intValue(), 0);
        assertEquals(s2.mod(new SerialNumber(BigInteger.valueOf(20))).intValue(), 2);
        assertEquals(s3.mod(new SerialNumber(BigInteger.valueOf(20))).intValue(), 1);
        assertEquals(s4.mod(new SerialNumber(BigInteger.valueOf(111))).intValue(), 90);
        assertEquals(s5.mod(new SerialNumber(BigInteger.valueOf(56))).intValue(), 5);
    }

    @Test
    public void testTestBit() throws Exception {
        assertEquals(s1.testBit(2), false);
    }

    @Test
    public void testIsEven() throws Exception {
        assertEquals(s1.isEven(), true);
        assertEquals(s2.isEven(), true);
        assertEquals(s3.isEven(), false);
        assertEquals(s4.isEven(), false);
        assertEquals(s5.isEven(), false);
    }

    @Test
    public void testIsOdd() throws Exception {
        assertEquals(s1.isOdd(), false);
        assertEquals(s2.isOdd(), false);
        assertEquals(s3.isOdd(), true);
        assertEquals(s4.isOdd(), true);
        assertEquals(s5.isOdd(), true);
    }
}