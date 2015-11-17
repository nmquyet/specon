/**
 * NumberComparable.java
 *
 * Created by Quyet. Nguyen Minh <quyetnm@vng.com.vn> on Jul 30, 2014.
 * Do not copy or use this source code without owner permission
 *
 * Copyright (c) VNG Corporation 2014. All rights reserved.
 *
 */
package vn.common.specs;

import vn.com.vng.common.utils.AssertUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


/**
 * Class which supports {@link Number} comparison to use with {@link PropertySpecification}
 */
public class NumericValue<T extends Number> extends AbstractSpecificationValue<T> {

    private BigDecimal value;

    @SuppressWarnings("unused")
    NumericValue() {}

    public NumericValue(String value) {
        AssertUtils.assertNotNull(value, "Value is required");
        this.value = new BigDecimal(value);
    }

    @Override
    public boolean isGreaterThan(T target) {
        return target != null && 0 < value.compareTo(sanitizeTarget(target));
    }

    @Override
    public boolean isLessThan(T target) {
        return target != null && 0 > value.compareTo(sanitizeTarget(target));
    }

    @Override
    public boolean isEquals(T target) {
        return target != null && 0 == value.compareTo(sanitizeTarget(target));
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Not support contains()");
    }

    @Override
    public List<Operator> supportedOperators() {
        return Arrays.asList(Operator.EQUAL, Operator.NOT, Operator.GREATER_THAN, Operator.GREATER_THAN_EQUAL, Operator.LESS_THAN, Operator.LESS_THAN_EQUAL);
    }

    private BigDecimal sanitizeTarget(Number target) {
        if (target instanceof BigDecimal) {
            return (BigDecimal) target;
        }
        else {
            return new BigDecimal(target.toString());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T value() {
        return (T) this.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String stringVal() {
        return this.value.toString();
    }

}
