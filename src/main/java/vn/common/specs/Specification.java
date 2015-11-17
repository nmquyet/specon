/**
 * Specification.java
 *
 * Created by Quyet. Nguyen Minh <quyetnm@vng.com.vn> on Jul 22, 2014.
 * Do not copy or use this source code without owner permission
 *
 * Copyright (c) VNG Corporation 2014. All rights reserved.
 *
 */
package vn.common.specs;

/**
 * Specification to verify an object
 */
public interface Specification<T> {

    /**
     * Verify an object
     * @return <code>true</code> if specification is satisfied by given object, otherwise <code>false</code>
     */
    boolean isSatisfiedBy(T object);
}
