package vn.common.specs.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.common.specs.meta.LuckyWheelUserMeta;
import vn.common.specs.domain.model.LuckyWheelUser;

/**
 * Specification parser for {@link LuckyWheelUser} object
 */
@Service
public class UserSpecificationParser extends SpecificationParser<LuckyWheelUser> {

    UserSpecificationParser() {}

    @Autowired
    public UserSpecificationParser(LuckyWheelUserMeta objectSpecificationMeta) {
        super(objectSpecificationMeta);
    }
}
