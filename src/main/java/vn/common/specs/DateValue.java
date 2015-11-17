/**
 * DateValue.java
 *
 * Created by Quyet. Nguyen Minh <quyetnm@vng.com.vn> on Jul 30, 2014.
 * Do not copy or use this source code without owner permission
 *
 * Copyright (c) VNG Corporation 2014. All rights reserved.
 *
 */
package vn.common.specs;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static vn.com.vng.common.utils.AssertUtils.assertNotNull;

/**
 * Comparable date
 */
public class DateValue extends AbstractSpecificationValue<Date> {

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX";

    private String dateFormat = DEFAULT_DATE_FORMAT;

    private Date value;

    @SuppressWarnings("unused")
    DateValue() {
        // Needed by Jackson
    }

    /**
     * @param date String representation of date in default format <code>yyyy-MM-dd'T'HH:mm:ssXXX</code>
     */
    public DateValue(Date date) {
        this(date, null);
    }

    /**
     * @param date Date string
     * @param dateFormat Date format string
     */
    protected DateValue(Date date, String dateFormat) {
        assertNotNull(date, "Date is required");
        this.value = date;
        if (dateFormat != null) {
            this.dateFormat = dateFormat;
        }
    }

    @Override
    public boolean isGreaterThan(Date target) {
        return target != null && value().after(target);
    }

    @Override
    public boolean isLessThan(Date target) {
        return target != null && value().before(target);
    }

    @Override
    public boolean isEquals(Date target) {
        return target != null && value().getTime() == target.getTime();
    }

    @Override
    public List<Operator> supportedOperators() {
        return Arrays.asList(
                Operator.EQUAL,
                Operator.NOT,
                Operator.GREATER_THAN,
                Operator.GREATER_THAN_EQUAL,
                Operator.LESS_THAN,
                Operator.LESS_THAN_EQUAL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date value() {
        return this.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Not supported");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String stringVal() {
        return dateFormat().format(this.value);
    }

    // =========================== PRIVATE HELPER METHODS ===========================

    /**
     * @return SimpleDateFormat which use current date format setting
     */
    private SimpleDateFormat dateFormat() {
        return new SimpleDateFormat(this.dateFormat);
    }

}
