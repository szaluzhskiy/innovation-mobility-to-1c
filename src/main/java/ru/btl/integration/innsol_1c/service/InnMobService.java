package ru.btl.integration.innsol_1c.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InnMobService {

  FtpClientService ftpClientService;

  public void uploadEventToSFTP(String event) throws IOException {
    ftpClientService.uploadJSON(event, generateFileName());
  }

  private String generateFileName() {
    return "im" + new SimpleDateFormat("HHmmssSSS").format(new Date()) + ".json";
  }
}
