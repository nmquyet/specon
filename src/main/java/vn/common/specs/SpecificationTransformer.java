package vn.common.specs;

/**
 * Interface which provides method to transform specification to something else
 * @param <T> the type to which specification is transformed
 */
public interface SpecificationTransformer<T> {

    /**
     * Convert given specification
     *
     * @param spec the specification to transform
     * @return the transformed object
     */
    T transform(Specification spec);
}
