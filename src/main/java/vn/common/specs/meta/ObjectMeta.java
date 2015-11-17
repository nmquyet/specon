package vn.common.specs.meta;

import vn.common.specs.PropertySpecification;

import java.util.HashMap;
import java.util.Map;

/**
 * Class provides information to compose {@link PropertySpecification} for different properties of an object
 * @author Nguyen Minh Quyet <minhquyet@gmail.com>
 * @param <T> The object type
 */
public abstract class ObjectMeta<T> {

    private Class<T> targetClass;

    private Map<String, PropertyMeta> properties;

    /**
     * Constructor
     * @param targetClass The type of described object
     */
    public ObjectMeta(Class<T> targetClass) {
        this.targetClass = targetClass;
        this.properties = new HashMap<>();
        initAdditionalProperties(this.properties);
    }

    /**
     * @return The type of described object
     */
    public Class<T> targetClass() {
        return this.targetClass;
    }

    public Map<String, PropertyMeta> properties() {
        return this.properties;
    }

    public void addProperty(String name, PropertyMeta descriptor) {
        if (this.properties == null) {
            this.properties = new HashMap<>();
        }
        this.properties.put(name, descriptor);
    }

    public boolean hasProperty(String name) {
        return this.properties.containsKey(name);
    }

    public PropertyMeta getProperty(String name) {
        return this.properties.get(name);
    }

    /**
     * Contract method used to init additional property descriptors in child class
     * @param properties the descriptor map. Never null
     */
    protected abstract void initAdditionalProperties(Map<String, PropertyMeta> properties);

}
