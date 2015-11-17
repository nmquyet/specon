package vn.common.specs.command;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * @author qunguyen
 */
public class SpecificationDescriptor {

    @NotEmpty
    private String targetType;

    /**
     * aggregate | property
     */
    @NotEmpty
    private String type;

    private String propertyName;

    private String operator;

    private String aggregator;

    private String value;

    private List<SpecificationDescriptor> innerSpecs;

    public String getType() {
        if (type != null) {
            return type;
        }

        if (aggregator != null && !"".equals(aggregator)) {
            return "aggregate";
        }

        return "property";
    }

    public String getOperator() {
        if (this.operator != null) {
            return this.operator.toUpperCase();
        }

        return "";
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<SpecificationDescriptor> getInnerSpecs() {
        return innerSpecs;
    }

    public void setInnerSpecs(List<SpecificationDescriptor> innerSpecs) {
        this.innerSpecs = innerSpecs;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getAggregator() {
        return aggregator;
    }

    public void setAggregator(String aggregator) {
        this.aggregator = aggregator;
    }
}

