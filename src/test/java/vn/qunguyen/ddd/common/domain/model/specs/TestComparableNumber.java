package vn.qunguyen.ddd.common.domain.model.specs;

import junit.framework.TestCase;
import vn.common.specs.NumericValue;

import java.math.BigDecimal;

/**
 * @author qunguyen
 */
public class TestComparableNumber extends TestCase {

    public void testComparableNumberBigDecimal() {

        NumericValue<Number> value = new NumericValue<>("10.1");

        try {
            value.contains(1);
            assertTrue(false);
        }
        catch (UnsupportedOperationException e) {
            assertTrue(true);
        }

        // Integer
        assertTrue(value.isLessThan(11));
        assertFalse(value.isEquals(11));
        assertFalse(value.isGreaterThan(11));

        assertTrue(value.isGreaterThan(10));
        assertFalse(value.isLessThan(10));
        assertFalse(value.isEquals(10));

        // Long
        assertTrue(value.isLessThan(11L));
        assertFalse(value.isEquals(11L));
        assertFalse(value.isGreaterThan(11L));

        // Float
        assertTrue(value.isLessThan(10.11f));
        assertFalse(value.isEquals(10.11f));
        assertFalse(value.isGreaterThan(10.11f));

        assertTrue(value.isEquals(10.1f));
        assertFalse(value.isGreaterThan(10.1f));
        assertFalse(value.isLessThan(10.1f));

        assertTrue(value.isEquals(10.1000000f));
        assertFalse(value.isLessThan(10.1000000f));
        assertFalse(value.isGreaterThan(10.1000000f));

        // Double
        assertTrue(value.isLessThan(10.12d));
        assertFalse(value.isEquals(10.12d));
        assertFalse(value.isGreaterThan(10.12d));

        assertTrue(value.isGreaterThan(0010.010000d));
        assertFalse(value.isLessThan(0010.010000d));
        assertFalse(value.isEquals(0010.010000d));

        assertTrue(value.isEquals(10.1d));
        assertFalse(value.isGreaterThan(10.1d));
        assertFalse(value.isLessThan(10.1d));

        assertTrue(value.isEquals(10.10d));
        assertFalse(value.isLessThan(10.10d));
        assertFalse(value.isGreaterThan(10.10d));

        assertTrue(value.isEquals(10.100d));
        assertFalse(value.isLessThan(10.100d));
        assertFalse(value.isGreaterThan(10.100d));

        // BigDecimal
        assertTrue(value.isLessThan(new BigDecimal("10.12")));
        assertFalse(value.isEquals(new BigDecimal("10.12")));
        assertFalse(value.isGreaterThan(new BigDecimal("10.12")));

        assertTrue(value.isGreaterThan(new BigDecimal("009.12")));
        assertFalse(value.isLessThan(new BigDecimal("009.12")));
        assertFalse(value.isEquals(new BigDecimal("009.12")));

        assertTrue(value.isEquals(new BigDecimal("10.1")));
        assertFalse(value.isLessThan(new BigDecimal("10.1")));
        assertFalse(value.isGreaterThan(new BigDecimal("10.1")));

        assertTrue(value.isEquals(new BigDecimal("10.10000")));
        assertFalse(value.isLessThan(new BigDecimal("10.10000")));
        assertFalse(value.isGreaterThan(new BigDecimal("10.10000")));

        // Negative value
        assertTrue(value.isGreaterThan(-20000000));
        assertFalse(value.isEquals(-20000000));
        assertFalse(value.isLessThan(-20000000));

    }
}
