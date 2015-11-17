package vn.common.specs;

/**
 * @author qunguyen
 */
public class SpecificationEntity<T> {
    private String id;
    private Specification<T> specification;

    SpecificationEntity() {}

    public SpecificationEntity(String id, Specification<T> specification) {
        this.id = id;
        this.specification = specification;
    }

    public String id() {
        return id;
    }

    public Specification<T> specification() {
        return specification;
    }
}
