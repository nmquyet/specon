//
// AbstractAggregateSpecification.java
//
// Created by Quyet. Nguyen Minh <quyetnm@vng.com.vn> on Jul 28, 2014.
// Do not copy or use this source code without owner permission
//
// Copyright (c) 123Pay 2014. All rights reserved.
//
//
package vn.common.specs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;


/**
 * Property specification which verifies specific property of an object instead of the whole object.
 * This class could be used in conjunction with {@link AggregateSpecification} and {@link Aggregator}
 * to define a complex specification for an object
 */
public class PropertySpecification<T> implements Specification<T> {

    // Logger
    private final static Logger LOG = LoggerFactory.getLogger(PropertySpecification.class);

    // Object attribute to validate
    private String propertyName;

    // Specification operator to check
    private Operator operator;

    // Specification Value
    private SpecificationValue<?> refVal;

    @SuppressWarnings("unused")
    PropertySpecification() {
        // Needed by Jackson Json
    }

    /**
     * Constructor
     * @param propertyName The property name
     * @param operator The operator
     * @param refVal The value to compared with property name
     */
    public PropertySpecification(String propertyName, Operator operator, SpecificationValue<?> refVal) {
        this.propertyName = propertyName;
        this.operator = operator;
        this.refVal = refVal;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSatisfiedBy(T candidate) {
        return validate(extractPropertyValueFrom(candidate));
    }

    /**
     * Validate with current specification operator and value
     * @param candidateValue Value to be validated
     * @return <code>true</code> if specification is satisfied, otherwise <code>false</code>
     */
    private boolean validate(Object candidateValue) {
        // Always return false if candidate is null
        if (candidateValue == null || this.refVal == null) {
            LOG.debug("Value of '" + propertyName() + "' is null");
            return false;
        }
        return this.operator.verify(refVal, candidateValue);
    }

    /**
     * Retrieve validated value from object.
     * This implementation use reflection to get retrieve value from object
     * @param obj Object that contains the attribute
     * @return To be validated object or null if attribute does not exist
     */
    private Object extractPropertyValueFrom(T obj) {
        try {
            // Get corresponding field from object and open access to it
            Field attribField = obj.getClass().getDeclaredField(propertyName());
            attribField.setAccessible(true);

            // Retrieve field value and return
            return attribField.get(obj);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            LOG.warn("'{}' while access property [{}] of object [{}]", e.getMessage(), propertyName(), obj.getClass());
            return null;
        }
    }

    // ============================= PROPERTIES ================================

    /**
     * @return Attribute name to validate
     */
    public String propertyName() {
        return this.propertyName;
    }

    /**
     * @return the operator
     */
    public Operator operator() {
        return this.operator;
    }

    /**
     * @return the value to validate against
     */
    public SpecificationValue<?> refVal() {
        return this.refVal;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }
}