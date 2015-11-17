package vn.common.specs.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vn.common.specs.Specification;
import vn.common.specs.SpecificationEntity;
import vn.common.specs.SpecificationRepository;
import vn.common.specs.command.SpecificationDescriptor;
import vn.common.specs.domain.model.LuckyWheelUser;
import vn.common.specs.parser.UserSpecificationParser;

import javax.validation.Valid;
import java.util.UUID;

/**
 * @author qunguyen
 */
@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    SpecificationRepository specificationRepository;

    @Autowired
    UserSpecificationParser specificationParser;

    @RequestMapping(method = RequestMethod.POST, value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@Valid @RequestBody SpecificationDescriptor specificationDescriptor) {

        Specification<LuckyWheelUser> userSpec = specificationParser.parse(specificationDescriptor);

        String id = UUID.randomUUID().toString();
        this.specificationRepository.save(new SpecificationEntity<LuckyWheelUser>(id, userSpec));

        SpecificationEntity<LuckyWheelUser> test = specificationRepository.getById(id);
        System.out.println(test.specification().isSatisfiedBy(new LuckyWheelUser(10)));
        System.out.println(test.specification().isSatisfiedBy(new LuckyWheelUser(4)));
        System.out.println(test.specification().isSatisfiedBy(new LuckyWheelUser(5)));
    }
}
