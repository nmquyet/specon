package vn.common.specs.parser;

import vn.common.specs.meta.ObjectMeta;
import vn.common.specs.meta.PropertyOption;
import vn.common.specs.meta.PropertyMeta;
import vn.common.specs.meta.SpecificationValueType;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author qunguyen
 */
public class TestUserMeta extends ObjectMeta<TestUser> {

    private static final String VIP_LEVEL   = "vipLevel";

    private static Map<String, SpecificationValueType> propertyValueMap;

    public TestUserMeta() {
        super(TestUser.class);
    }

    @Override
    protected void initAdditionalProperties(Map<String, PropertyMeta> properties) {

        // Vip property specification descriptor
        List<PropertyOption> vipLevelValueOptions =
                Arrays.asList(new PropertyOption("0", "NEW USER"),
                              new PropertyOption("1", "VIP 1"),
                              new PropertyOption("2", "VIP 2"),
                              new PropertyOption("3", "VIP 3"),
                              new PropertyOption("4", "VIP 4"),
                              new PropertyOption("5", "VIP 5"),
                              new PropertyOption("6", "VIP 6"),
                              new PropertyOption("7", "VIP 7"),
                              new PropertyOption("8", "VIP 8"));

        properties.put(VIP_LEVEL, new PropertyMeta(VIP_LEVEL,
                                                         "VIP Level",
                                                          SpecificationValueType.NUMERIC,
                                                          vipLevelValueOptions));
    }
}
