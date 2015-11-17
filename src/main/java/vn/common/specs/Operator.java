/**
 * ConditionOperator.java
 *
 * Created by Quyet. Nguyen Minh <quyetnm@vng.com.vn> on Jul 22, 2014.
 * Do not copy or use this source code without owner permission
 *
 * Copyright (c) VNG Corporation 2014. All rights reserved.
 *
 */
package vn.common.specs;

import vn.com.vng.common.utils.StringUtils;

/**
 * Specification operator used to define a specification
 *
 * <p>EQUAL (<code>==</code>) and NOT (<code>!=</code>) operators.
 * <p>
 * <code>requirement</code> and <code>validatedValue</code> must be one of following
 * <ul>
 * <li>java.lang.String</li>
 * <li>java.lang.Collection</li>
 * <li>Primitive Wrapper</li>
 * </ul>
 *
 * <p>GREATER_THAN (<code>&gt;</code>), GREATER_THAN_EQUAL (<code>&gt;=</code>),
 * LESS_THAN (<code>&lt;</code>) and LESS_THAN_EQUAL (<code>&lt;=</code>)
 * <p>
 * <code>requirement</code> and <code>validatedValue</code> must be one of following
 * <ul>
 * <li>java.lang.String</li>
 * <li>Primitive Wrapper</li>
 * </ul>
 *
 * <p>SUBSET OR NOT A SUBSET operator
 * <p>
 * <code>requirement</code> must be one of following
 * <ul>
 * <li>java.lang.Collection</li>
 * </ul>
 * <p>
 * <code>validatedValue</code> must be one following
 * <ul>
 * <li>java.lang.String</li>
 * <li>java.lang.Collection</li>
 * </ul>
 *
 */
@SuppressWarnings("unchecked")
public enum Operator {

    EQUAL("=") {
        @Override
        public <T> boolean verify(SpecificationValue<T> requirement, Object candidate) {
            return requirement.isEquals((T) candidate);
        }

    },

    NOT("!=") {
        @Override
        public <T> boolean verify(SpecificationValue<T> requirement, Object candidate) {
            return !requirement.isEquals((T) candidate);
        }
    },

    GREATER_THAN(">") {
        @Override
        public <T> boolean verify(SpecificationValue<T> requirement, Object candidate) {
            return requirement.isLessThan((T) candidate);
        }
    },

    GREATER_THAN_EQUAL(">=") {
        @Override
        public <T> boolean verify(SpecificationValue<T> requirement, Object candidate) {
            return requirement.isLessThan((T) candidate) || requirement.isEquals((T) candidate);
        }
    },

    LESS_THAN("<") {
        @Override
        public <T> boolean verify(SpecificationValue<T> requirement, Object candidate) {
            return requirement.isGreaterThan((T) candidate);
        }
    },

    LESS_THAN_EQUAL("<=") {
        @Override
        public <T> boolean verify(SpecificationValue<T> requirement, Object candidate) {
            return requirement.isGreaterThan((T) candidate) || requirement.isEquals((T) candidate);
        }
    },

    IN("{}") {
        @Override
        public <T> boolean verify(SpecificationValue<T> requirement, Object candidate) {
            return requirement.contains(candidate);
        }
    },

    NOT_IN("!{}") {
        @Override
        public <T> boolean verify(SpecificationValue<T> requirement, Object candidate) {
            return !requirement.contains(candidate);
        }

    };

    private String symbol;

    Operator(String symbol) {
        this.symbol = symbol;
    }

    /**
     * @param requirement the predefined values used to verify
     * @param candidate the candidate requirement to verify
     * @return <code>true</code> or <code>false</code>
     */
    public abstract <T> boolean verify(SpecificationValue<T> requirement, Object candidate);

    public Operator parse(String value) {
        return Operator.valueOf(value.toUpperCase());
    }

    /**
     * Get operator instance from representational symbol
     * @param symbol The symbol
     * @return the operator
     * @throws IllegalArgumentException when provided symbol is invalid
     */
    public static Operator fromSymbol(String symbol) {
        if (StringUtils.isEmpty(symbol)) {
            throw new IllegalArgumentException("Invalid specification operator symbol");
        }
        for (Operator op : Operator.values()) {
            if (symbol.equals(op.symbol)) {
                return op;
            }
        }

        throw new IllegalArgumentException("Invalid specification operator symbol");
    }
}
