package ru.btl.integration.innsol_1c;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.btl.integration.innsol_1c.config.FTPClientProperties;

@SpringBootApplication
@EnableConfigurationProperties(FTPClientProperties.class)
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
