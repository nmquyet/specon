package vn.common.specs.meta;

import vn.com.vng.common.utils.AssertUtils;

/**
 * An property's option
 * @author qunguyen
 */
public class PropertyOption {
    private String displayName;
    private String value;

    public PropertyOption(String value, String displayName) {
        AssertUtils.assertNotNull(displayName, "Option's display name is required");
        AssertUtils.assertNotNull(value, "Option's value is required");
        this.displayName = displayName;
        this.value = value;
    }

    public String displayName() {
        return displayName;
    }

    public String value() {
        return value;
    }
}
