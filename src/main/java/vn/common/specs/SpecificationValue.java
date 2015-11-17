package vn.common.specs;

import java.util.List;

/**
 * A value that could be used with {@link PropertySpecification}
 * @param <T> The actual value type
 */
public interface SpecificationValue<T> {

    boolean isGreaterThan(T target);

    boolean isLessThan(T target);

    boolean isEquals(T target);

    List<Operator> supportedOperators();

    boolean contains(Object o);

    T value();

    String stringVal();
}