package vn.qunguyen.ddd.common.domain.model.specs;

import junit.framework.TestCase;
import vn.common.specs.DateValue;
import vn.common.specs.SpecificationValue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author qunguyen
 */
public class TestComparableDate extends TestCase {

    public void testComparableDate() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

        // UTC+7
        DateValue date = new DateValue(df.parse("2015-10-01T00:00:00+07:00"));

        assertTrue(date.isGreaterThan(df.parse("2015-09-30T12:00:00+07:00")));

        assertFalse(date.isEquals(df.parse("2015-10-01T00:00:01+07:00")));
        assertTrue(date.isEquals(df.parse("2015-10-01T00:00:00+07:00")));

        assertTrue(date.isLessThan(df.parse("2015-10-01T12:00:00+07:00")));
        assertTrue(date.isLessThan(df.parse("2015-10-02T00:00:00+07:00")));

        // Different time zone (UTC+12)
        assertTrue(date.isEquals(df.parse("2015-10-01T05:00:00+12:00")));
        assertTrue(date.isGreaterThan(df.parse("2015-10-01T04:00:00+12:00")));
        assertTrue(date.isLessThan(df.parse("2015-10-01T06:00:00+12:00")));

        System.out.println(date.stringVal());

        // Verify not supported operation
        try {
            date.contains(new Date());
            assertTrue(false);
        }
        catch (UnsupportedOperationException e) {
            assertTrue(true);
        }
    }
}
