package danielh1307.springboothateoasexample.domain;

public class InfoMessageResponse<T> {

    private T data;

    public InfoMessageResponse(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

}
