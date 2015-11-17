package vn.common.specs.parser;

import org.springframework.stereotype.Service;
import vn.common.specs.*;
import vn.common.specs.command.SpecificationDescriptor;
import vn.common.specs.meta.ObjectMeta;
import vn.common.specs.meta.PropertyMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qunguyen
 */
@Service
public class SpecificationParser<T> {

    private ObjectMeta<T> objectMeta;

    SpecificationParser() {}

    /**
     * Constructor with default json object mapper
     * @param objectMeta the object specification meta
     */
    public SpecificationParser(ObjectMeta<T> objectMeta) {
        this.objectMeta = objectMeta;
    }

    /**
     * Parse specification using {@link SpecificationDescriptor} value object
     * @param specificationDescriptor the specification descriptor object
     * @return the parsed specification
     */
    public Specification<T> parse(SpecificationDescriptor specificationDescriptor) {
        return createSpec(specificationDescriptor);
    }

    /**
     * Recursively create and return composite (aggregate) specification from a specification descriptor
     * @param specificationDescriptor the specification descriptor
     * @return the specification object
     * @throws IllegalArgumentException when specification contains invalid string representational value
     */
    private Specification<T> createSpec(SpecificationDescriptor specificationDescriptor) {

        Specification<T> spec;

        // Create AggregateSpecification if spec type is "aggregate"
        if ("aggregate".equalsIgnoreCase(specificationDescriptor.getType())) {
            String aggregator = specificationDescriptor.getAggregator();

            List<Specification<T>> aggregateSpecs = new ArrayList<>();
            for (SpecificationDescriptor innerSpecificationDescriptor : specificationDescriptor.getInnerSpecs()) {
                aggregateSpecs.add(createSpec(innerSpecificationDescriptor));
            }

            spec = new AggregateSpecification<>(Aggregator.parse(aggregator), aggregateSpecs);
            return spec;

        }
        // Create PropertySpecification if spec type is "property"
        else if ("property".equalsIgnoreCase(specificationDescriptor.getType())) {
            return new PropertySpecification<>(specificationDescriptor.getPropertyName(),
                                               Operator.fromSymbol(specificationDescriptor.getOperator()),
                                               createSpecificationValueFor(specificationDescriptor));
        }

        throw new IllegalArgumentException("validation.new_specification.invalid_specification_type");
    }

    /**
     * Create and return specification value from a specification descriptor
     * @param specificationDescriptor the specification descriptor
     * @return specification value
     * @throws IllegalArgumentException when specification has invalid string representational value
     */
    private SpecificationValue<?> createSpecificationValueFor(SpecificationDescriptor specificationDescriptor) {
        PropertyMeta propDescriptor = this.objectMeta.getProperty(specificationDescriptor.getPropertyName());
        if (propDescriptor == null) {
            throw new IllegalArgumentException("Invalid property '" + specificationDescriptor.getPropertyName() + "'");
        }
        return propDescriptor.specificationValueType().createSpecificationValueFrom(specificationDescriptor.getValue());
    }

}
