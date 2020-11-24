package ru.btl.integration.innsol_1c.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@ConfigurationProperties("app.innov.ftp")
@Data
public class FTPClientProperties {
  private String remoteHost;
  private int remotePort;
  private String user;
  private String password;
  private String targetFolder;
}
