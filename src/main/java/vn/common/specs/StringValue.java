/**
 * StringValue.java
 *
 * Created by Quyet. Nguyen Minh <quyetnm@vng.com.vn> on Jul 30, 2014.
 * Do not copy or use this source code without owner permission
 *
 * Copyright (c) VNG Corporation 2014. All rights reserved.
 *
 */
package vn.common.specs;

import vn.com.vng.common.utils.AssertUtils;
import vn.com.vng.common.utils.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Class supports {@link String} comparison to use with {@link PropertySpecification}
 * with following type of comparison
 * <ul>
 * <li>Exact match (case-insensitive)</li>
 * <li>Begins with</li>
 * </ul>
 */
public class StringValue extends AbstractSpecificationValue<String> {

    private static final String SEPARATOR = ";;";
    private Set<String> values;

    @SuppressWarnings("unused")
    StringValue() {
        // Needed by Jackson Json
    }

    public StringValue(String values) {
        AssertUtils.assertNotNull(values, "Value is required");
        this.values = new HashSet<>();
        String[] splitValues = values.split(SEPARATOR);
        for (String val : splitValues) {
            if (StringUtils.isEmpty(val)) {
                continue;
            }

            this.values.add(val.trim());
        }
    }

    @Override
    public boolean isGreaterThan(String target) {
        throw new UnsupportedOperationException("String does not support > operator");
    }

    @Override
    public boolean isLessThan(String target) {
        throw new UnsupportedOperationException("String does not support < operator");
    }

    @Override
    public boolean isEquals(String target) {
        return target != null && this.values.size() != 0 && this.values.contains(target);
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) return false;

        String candidate;
        if (!String.class.isInstance(o)) {
            candidate = o.toString();
        } else {
            candidate = (String) o;
        }

        return this.values.contains(candidate);
    }

    @Override
    public List<Operator> supportedOperators() {
        return Arrays.asList(Operator.EQUAL, Operator.NOT, Operator.IN, Operator.NOT_IN);
    }

    @Override
    public String value() {
        return this.stringVal();
    }

    @Override
    public String stringVal() {
        return StringUtils.join(this.values, SEPARATOR);
    }

}
