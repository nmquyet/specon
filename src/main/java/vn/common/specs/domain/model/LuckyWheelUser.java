package vn.common.specs.domain.model;

/**
 * @author qunguyen
 */
public class LuckyWheelUser {
    private int vipLevel;
    private String gender;

    public LuckyWheelUser(int vipLevel) {
        this.vipLevel = vipLevel;
    }

    public int vipLevel() {
        return vipLevel;
    }


}
