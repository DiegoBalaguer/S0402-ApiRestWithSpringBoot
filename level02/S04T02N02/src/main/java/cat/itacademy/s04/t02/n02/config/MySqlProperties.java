package cat.itacademy.s04.t02.n02.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mysql")
@Getter
@Setter
public class MySqlProperties {
    private boolean sshEnable;
    private String driver;
    private String url;
    private String host;
    private int localPort;
    private String schema;
    private String user;
    private String password;
    private int remotePort;
}
