package vn.qunguyen.ddd.common.domain.model.specs;

import junit.framework.TestCase;
import vn.common.specs.StringValue;

/**
 * @author qunguyen
 */
public class TestComparableString extends TestCase {

    public void testComparableString() {
        StringValue stringSpec = new StringValue("Hello, Test Case;; world  ;;");

        // exact match
        assertTrue(stringSpec.isEquals("Hello, Test Case"));
        assertTrue(stringSpec.contains("Hello, Test Case"));
        assertFalse(stringSpec.isEquals("hello, test cAse"));
        assertFalse(stringSpec.contains("hello, test cAse"));
        assertTrue(stringSpec.contains("world"));
        assertFalse(stringSpec.contains(""));

        assertEquals("Hello, Test Case;;world", stringSpec.stringVal());

        try {
            // original length > input
            stringSpec.isLessThan("not care");

            // could not reach here
            assertTrue(false);
        } catch (UnsupportedOperationException e) {
            assertTrue(true);
        }

        try {
            stringSpec.isGreaterThan("anything");

            // could not reach here
            assertTrue(false);
        } catch (UnsupportedOperationException e) {
            assertTrue(true);
        }

    }

}
