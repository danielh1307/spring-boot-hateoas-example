package danielh1307.springboothateoasexample.ctrl;

import danielh1307.springboothateoasexample.domain.Greeting;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.String.format;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/greeting")
class GreetingController {

    @GetMapping("/{name}")
    HttpEntity<Greeting> greeting(@PathVariable String name) {
        Greeting greeting = new Greeting(format("Hello, %s", name));

        HttpEntity<Greeting> greetingMethod = methodOn(GreetingController.class).greeting(name);
        Link greetingLink = linkTo(greetingMethod).withSelfRel();
        greeting.add(greetingLink);

        return new ResponseEntity<>(greeting, OK);
    }

}
