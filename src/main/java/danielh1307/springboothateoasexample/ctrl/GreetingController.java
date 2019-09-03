package danielh1307.springboothateoasexample.ctrl;

import danielh1307.springboothateoasexample.domain.Greeting;
import danielh1307.springboothateoasexample.domain.InfoMessageResponse;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class GreetingController {

    @RequestMapping("/greeting/{name}")
    public HttpEntity<Greeting> greeting(@PathVariable String name) {
        Greeting greeting = new Greeting(String.format("Hello, %s", name));

        HttpEntity<Greeting> greetingMethod = ControllerLinkBuilder.methodOn(GreetingController.class).greeting(name);
        Link greetingLink = ControllerLinkBuilder.linkTo(greetingMethod).withSelfRel();
        greeting.add(greetingLink);

        return new ResponseEntity<>(greeting, OK);
    }

    @GetMapping(value = "/greetingInfoMessage/{name}", produces = {"application/json", "application/hal+json"})
    public Resource<InfoMessageResponse<Greeting>> greetingInfoMessageResponse(@PathVariable String name) {
        Greeting greeting = new Greeting(String.format("Hello, %s", name));

        HttpEntity<Greeting> greetingMethod = ControllerLinkBuilder.methodOn(GreetingController.class).greeting(name);
        Link greetingLink = ControllerLinkBuilder.linkTo(greetingMethod).withSelfRel();

        greeting.add(greetingLink);

        return new Resource<>(new danielh1307.springboothateoasexample.domain.InfoMessageResponse<>(greeting));
    }


}
