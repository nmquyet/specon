package vn.common.specs.meta;

import vn.common.specs.Operator;

import java.util.Collections;
import java.util.List;

/**
 * Specification descriptor for a property
 * @author qunguyen
 */
public class PropertyMeta {

    private String name;

    private String displayName;

    private SpecificationValueType valueType;

    private List<PropertyOption> valueOptions;

    public PropertyMeta(String name, String displayName, SpecificationValueType valueType) {
        this(name, displayName, valueType, null);
    }

    public PropertyMeta(String name, String displayName, SpecificationValueType valueType, List<PropertyOption> valueOptions) {
        this.name = name;
        this.displayName = displayName;
        this.valueType = valueType;

        if (null != valueOptions) {
            this.valueOptions = valueOptions;
        }
        else {
            this.valueOptions = Collections.emptyList();
        }
    }

    public String name() {
        return name;
    }

    public String displayName() {
        return displayName;
    }

    public SpecificationValueType specificationValueType() {
        return valueType;
    }

    public List<PropertyOption> valueOptions() {
        return valueOptions;
    }

    public List<Operator> supportedOperators() {
        return this.valueType.operators();
    }
}
