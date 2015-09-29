package orange;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


import java.math.BigInteger;
import java.rmi.server.ExportException;
import java.util.*;
import java.util.regex.Matcher;

import static org.junit.Assert.*;

/**
 * Created by Andrew Hwang on 9/16/2015.
 */
public class OpodTest {

    private Opod o1;
    private SerialNumber serialNumber1;
    private Optional<Set<String>> description1;

    @Before
    public void setUp() throws Exception {
        serialNumber1 = new SerialNumber(BigInteger.valueOf(10000));

        Set<String> set1 = new HashSet<>();
        set1.add("This product was developed in Cleveland");
        set1.add("This is an oPod");
        set1.add("This is new");
        description1 = Optional.of(set1);

        o1 = new Opod(serialNumber1, description1);
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
        assertEquals(Opod.isValidSerialNumber(o1.getSerialNumber()), true);
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
}