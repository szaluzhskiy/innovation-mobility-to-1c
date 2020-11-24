package ru.btl.integration.innsol_1c.service;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InnMobService {
  @Value("app.innov.sftp.username")
  private String sftpUsername;
  @Value("app.innov.sftp.password")
  private String sftpPassword;
  @Value("app.innov.sftp.remoteHost")
  private String remoteHost;

  public void uploadEventToSFTP(String event, String sftpPath) {

  }

  private ChannelSftp setupJsch() throws JSchException {
    JSch jsch = new JSch();
    jsch.setKnownHosts("/Users/john/.ssh/known_hosts");
    Session jschSession = jsch.getSession(sftpUsername, remoteHost);
    jschSession.setPassword(sftpPassword);
    jschSession.connect();
    return (ChannelSftp) jschSession.openChannel("sftp");
  }
}
