package danielh1307.springboothateoasexample.domain;

import org.springframework.hateoas.ResourceSupport;

public class Greeting extends ResourceSupport {

    private final String content;

    public Greeting(String greeting) {
        this.content = greeting;
    }

    public String getContent() {
        return content;
    }
}
