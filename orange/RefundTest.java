package orange;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * Created by Andrew Hwang on 9/23/2015.
 */
public class RefundTest {

    Refund refund;
    Refund.Builder builder;
    BigInteger rma;

    @Before
    public void setUp() throws Exception {
        builder = new Refund.Builder();
        rma = BigInteger.valueOf(20);

        builder.setRma(rma);

        refund = builder.build();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetRma() throws Exception {
        assertEquals(refund.getRma(), builder.getRma());
    }
}