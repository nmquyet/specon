package vn.common.specs.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.common.specs.meta.LuckyWheelUserMeta;

/**
 * @author qunguyen
 */
@RestController
@RequestMapping("/meta")
public class SpecMetaResource {

    @Autowired
    LuckyWheelUserMeta luckyWheelUserSpecificationDescriptor;

    @RequestMapping("/users")
    public LuckyWheelUserMeta get() {
        return luckyWheelUserSpecificationDescriptor;
    }
}
