package danielh1307.springboothateoasexample.ctrl;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class PaymentController {

    @RequestMapping("/payment")
    public HttpEntity<Void> paymentTo(@RequestParam  String name) {
        return new ResponseEntity<>(OK);
    }
}
