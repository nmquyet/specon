package vn.common.specs.parser;

import junit.framework.TestCase;
import vn.common.specs.*;
import vn.common.specs.command.SpecificationDescriptor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @author qunguyen
 */
public class TestSpecParser extends TestCase {

    private SpecificationDescriptor createSpecDTO() {
        SpecificationDescriptor inner11 = new SpecificationDescriptor();
        inner11.setTargetType(TestUser.class.getCanonicalName());
        inner11.setPropertyName("vipLevel");
        inner11.setOperator(">");
        inner11.setValue("5");

        SpecificationDescriptor inner12 = new SpecificationDescriptor();
        inner12.setTargetType(TestUser.class.getCanonicalName());
        inner12.setPropertyName("vipLevel");
        inner12.setOperator("<");
        inner12.setValue("10");

        SpecificationDescriptor inner1 = new SpecificationDescriptor();
        inner1.setAggregator("all");
        inner1.setInnerSpecs(Arrays.asList(inner11, inner12));

        SpecificationDescriptor inner2 = new SpecificationDescriptor();
        inner2.setTargetType(TestUser.class.getCanonicalName());
        inner2.setPropertyName("vipLevel");
        inner2.setOperator("=");
        inner2.setValue("0");

        SpecificationDescriptor dto = new SpecificationDescriptor();
        dto.setAggregator("ANY");
        dto.setInnerSpecs(Arrays.asList(inner1, inner2));

        return dto;
    }

    public void testCreate() {
        SpecificationParser<TestUser> parser = new SpecificationParser<>(new TestUserMeta());
        Specification<TestUser> spec = parser.parse(createSpecDTO());

        /* Check root spec
         -------------------------------- */
        assertTrue(spec instanceof AggregateSpecification);
        AggregateSpecification<TestUser> rootSpec = (AggregateSpecification<TestUser>) spec;
        assertEquals(Aggregator.ANY, rootSpec.aggregator());
        List<Specification<TestUser>> inner = rootSpec.aggregates();
        assertEquals(2, inner.size());

        /* Check first child of root spec
         -------------------------------- */
        assertTrue(inner.get(0) instanceof AggregateSpecification);
        AggregateSpecification<TestUser> inner1  = (AggregateSpecification<TestUser>) inner.get(0);
        assertEquals(Aggregator.ALL, inner1.aggregator());
        List<Specification<TestUser>> inner1Children = inner1.aggregates();
        assertEquals(2, inner1Children.size());
        // Check first children of first child
        assertTrue(inner1Children.get(0) instanceof PropertySpecification);
        PropertySpecification p1 = (PropertySpecification) inner1Children.get(0);
        assertEquals("vipLevel", p1.propertyName());
        assertEquals(Operator.GREATER_THAN, p1.operator());
        assertEquals(new BigDecimal("5"), p1.refVal().value());
        // Check second children of first child
        assertTrue(inner1Children.get(1) instanceof PropertySpecification);
        PropertySpecification p2 = (PropertySpecification) inner1Children.get(1);
        assertEquals("vipLevel", p2.propertyName());
        assertEquals(Operator.LESS_THAN, p2.operator());
        assertEquals(new BigDecimal("10"), p2.refVal().value());

        /* Check second child of root spec
         -------------------------------- */
        assertTrue(inner.get(1) instanceof PropertySpecification);
        assertEquals("vipLevel", ((PropertySpecification) inner.get(1)).propertyName());
        assertEquals(Operator.EQUAL, ((PropertySpecification) inner.get(1)).operator());
        assertEquals(new BigDecimal(0), ((PropertySpecification) inner.get(1)).refVal().value());


        /* Verify correctness of created specification
        -------------------------------------------------------------- */
        TestUser user = new TestUser(9);
        assertTrue(spec.isSatisfiedBy(user));

        TestUser user1 = new TestUser(0);
        assertTrue(spec.isSatisfiedBy(user1));

        TestUser user2 = new TestUser(4);
        assertFalse(spec.isSatisfiedBy(user2));
    }
}
