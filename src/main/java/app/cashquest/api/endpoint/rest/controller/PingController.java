package app.cashquest.api.endpoint.rest.controller;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.TEXT_PLAIN;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {
    @GetMapping(value = "/ping", produces = TEXT_PLAIN_VALUE)
    public ResponseEntity<String> ping() {
        return ResponseEntity
                .status(OK)
                .contentType(TEXT_PLAIN)
                .body("pong");
    }
}
