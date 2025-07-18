package cat.itacademy.s04.t02.n03.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mongodb")
@Getter
@Setter
public class MongoDbProperties {
    private boolean sshEnable;
    private String url;
    private String host;
    private int localPort;
    private String schema;
    private String collection;
    private String user;
    private String password;
    private int remotePort;
}
