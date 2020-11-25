package ru.btl.integration.innsol_1c.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.btl.integration.innsol_1c.config.FTPClientProperties;

@Service
@Slf4j
public class FtpClientService {

  private final FTPClientProperties ftpClientProperties;

  private FTPClient ftp;

  @Autowired
  public FtpClientService(FTPClientProperties ftpClientProperties) {
    this.ftpClientProperties = ftpClientProperties;
  }

  private void open() throws IOException {
    ftp = new FTPClient();

    ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
    ftp.connect(ftpClientProperties.getRemoteHost(), ftpClientProperties.getRemotePort());
    ftp.enterLocalPassiveMode();
    int reply = ftp.getReplyCode();
    log.debug("FTP server response code {}", reply);
    if (!FTPReply.isPositiveCompletion(reply)) {
      ftp.disconnect();
      throw new IOException("Exception in connecting to FTP Server");
    }
    ftp.login(ftpClientProperties.getUser(), ftpClientProperties.getPassword());
  }

  public void uploadJSON(String json, String fileName) throws IOException {
    open();
    try (InputStream input = new ByteArrayInputStream(json.getBytes())) {
      ftp.storeFile(ftpClientProperties.getTargetFolder() + "/" + fileName, input);
    } finally {
      close();
    }
  }

  private void close() throws IOException {
    if (this.ftp.isConnected()) {
      try {
        this.ftp.logout();
        this.ftp.disconnect();
      } catch (IOException e) {
        log.error("Close failed", e);
        // do nothing as file is already saved to server
      }
    }
  }
}
