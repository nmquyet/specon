/**
 * SpecificationValue.java
 *
 * Created by Quyet. Nguyen Minh <quyetnm@vng.com.vn> on Jul 30, 2014.
 * Do not copy or use this source code without owner permission
 *
 * Copyright (c) VNG Corporation 2014. All rights reserved.
 *
 */
package vn.common.specs;

import java.util.Objects;

/**
 * Abstract class which supports implementing {@link SpecificationValue}
 */
public abstract class AbstractSpecificationValue<T> implements SpecificationValue<T> {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractSpecificationValue)) return false;
        AbstractSpecificationValue<?> that = (AbstractSpecificationValue<?>) o;
        return Objects.equals(value(), that.value());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value());
    }
}
