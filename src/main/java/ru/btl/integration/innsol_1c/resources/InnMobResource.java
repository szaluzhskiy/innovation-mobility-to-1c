package ru.btl.integration.innsol_1c.resources;

import static ru.btl.integration.innsol_1c.resources.ApiConstants.INNOVATION_MOBILITY_PATH;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface InnMobResource {

    @PostMapping(path = INNOVATION_MOBILITY_PATH + "/event")
    public ResponseEntity<String> uploadEvent(@RequestBody String event);
}
