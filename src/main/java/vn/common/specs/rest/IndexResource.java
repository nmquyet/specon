package vn.common.specs.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qunguyen
 */
@RestController
@RequestMapping("/")
public class IndexResource {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "It works";
    }
}
