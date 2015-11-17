package vn.common.specs;

/**
 * @author qunguyen
 */
public interface SpecificationRepository {

    void save(SpecificationEntity entity);
    <T> SpecificationEntity<T> getById(String id);

}
