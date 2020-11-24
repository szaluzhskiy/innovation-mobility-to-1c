package ru.btl.integration.innsol_1c.controller;

import java.io.IOException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.btl.integration.innsol_1c.resources.InnMobResource;
import ru.btl.integration.innsol_1c.service.InnMobService;

@RestController
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InnMobController implements InnMobResource {

  InnMobService innMobService;

  @Override
  public ResponseEntity<String> uploadEvent(String event) throws IOException {
    innMobService.uploadEventToSFTP(event);
    return new ResponseEntity<String>(HttpStatus.OK);
  }
}
