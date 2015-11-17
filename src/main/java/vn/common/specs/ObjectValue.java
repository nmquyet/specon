package vn.common.specs;

import java.util.List;

/**
 * Comparable object
 */
public class ObjectValue extends AbstractSpecificationValue<Object> {

    private Object value;

    public ObjectValue(Object value) {
        this.value = value;
    }

    @Override
    public boolean isGreaterThan(Object target) {
        return false;
    }

    @Override
    public boolean isLessThan(Object target) {
        return false;
    }

    @Override
    public boolean isEquals(Object target) {
        return false;
    }

    @Override
    public List<Operator> supportedOperators() {
        return null;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Not supported operation");
    }

    @Override
    public Object value() {
        return this.value;
    }

    @Override
    public String stringVal() {
        return value.toString();
    }
}
