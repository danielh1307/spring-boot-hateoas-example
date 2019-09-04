package danielh1307.springboothateoasexample.domain;

public class PlainGreeting {

    private final String content;

    public PlainGreeting(String greeting) {
        this.content = greeting;
    }

    public String getContent() {
        return content;
    }
}
