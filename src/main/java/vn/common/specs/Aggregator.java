/**
 * Aggregator.java
 *
 * Created by Quyet. Nguyen Minh <quyetnm@vng.com.vn> on Jul 29, 2014.
 * Do not copy or use this source code without owner permission
 *
 * Copyright (c) VNG Corporation 2014. All rights reserved.
 *
 */
package vn.common.specs;

/**
 * Types of an aggregate specification
 */
public enum Aggregator {
    /**
     * Require only one child to be satisfied
     */
    ANY,

    /**
     * Require all children to be satisfied
     */
    ALL,

    ;

    public static Aggregator parse(String textualValue) {
        try {
            return Aggregator.valueOf(textualValue.toUpperCase());
        }
        catch (Exception e) {
            throw new IllegalArgumentException("validation.aggregator.invalid_value");
        }
    }

}
