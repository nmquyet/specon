/**
 * AggregateSpecification.java
 *
 * Created by Quyet. Nguyen Minh <quyetnm@vng.com.vn> on Jul 29, 2014.
 * Do not copy or use this source code without owner permission
 *
 * Copyright (c) VNG Corporation 2014. All rights reserved.
 *
 */
package vn.common.specs;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite specification which holds reference to other specifications
 */
@SuppressWarnings("Duplicates")
public class AggregateSpecification<T> implements Specification<T> {

    private Aggregator aggregator;
    private List<Specification<T>> aggregates = new ArrayList<>();

    @SuppressWarnings("unused")
    AggregateSpecification() {}

    public AggregateSpecification(Aggregator aggregator, List<Specification<T>> aggregates) {
        this.aggregator = aggregator;
        this.aggregates = aggregates;

        if (this.aggregates == null) {
            this.aggregates = new ArrayList<>();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfiedBy(T candidate) {
        // ALL spec NEEDED to be SATISFIED
        if (aggregator() == Aggregator.ALL) {
            // If there is ONE is NOT SATISFIED, the WHOLE AGGREGATE is NOT SATISFIED
            for (Specification<T> s : aggregates()) {
                if (!s.isSatisfiedBy(candidate)) {
                    return false;
                }

            }

            // ALL spec are SATISFIED, WHOLE AGGREGATE is SATISFIED
            return true;
        }
        // Just ONE spec NEEDED to be SATISFIED.
        else if (aggregator() == Aggregator.ANY) {
            // If ONE spec is SATISFIED, the WHOLE AGGREGATE is SATISFIED
            for (Specification<T> s : aggregates()) {
                if (s.isSatisfiedBy(candidate)) {
                    return true;
                }
            }

            // There are NO spec is SATISFIED, the WHOLE AGGREGATE is NOT SATISFIED
            return false;
        } else {
            // Not recognized aggregator
            throw new RuntimeException("Unknown aggregator [" + aggregator().toString() + "]");
        }
    }

    public Aggregator aggregator() {
        return this.aggregator;
    }

    public List<Specification<T>> aggregates() {
        return aggregates;
    }

    public void addSpecification(Specification<T> spec) {
        this.aggregates.add(spec);
    }

}
