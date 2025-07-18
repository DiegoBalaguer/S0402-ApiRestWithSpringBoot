package cat.itacademy.s04.t02.n03.config;

import com.jcraft.jsch.*;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SSHManagerService {

    private static final Logger logger = LoggerFactory.getLogger(SSHManagerService.class);
    private final SSHProperties sshProp;
    private final MongoDbProperties mongoDbProp;

    // SSH connection
    private final boolean sshIsEnable;
    private final String sshHost;
    private final int sshPort;
    private final String sshUser;
    private final String sshPassword;
    private final String sshPrivateKeyPath;
    private final String sshPrivateKeyPassword;

    // Mongodb connection
    private final int mongoDbLocalPort;
    private final String mongoDbHost;
    private final int mongoDbRemotePort;

    private Session session;
    private JSch jsch;

    public SSHManagerService(SSHProperties sshProperties, MongoDbProperties mongoDbProperties) {
        this.sshProp = sshProperties;
        this.mongoDbProp = mongoDbProperties;

        // SSH connection
        sshIsEnable = sshProp.isEnable();
        sshHost = sshProp.getHost();
        sshPort = sshProp.getHostPort();
        sshUser = sshProp.getUser();
        sshPassword = sshProp.getPassword();
        sshPrivateKeyPath = sshProp.getPrivateKeyPath();
        sshPrivateKeyPassword = sshProp.getPrivateKeyPassword();

        // Mongodb connection
        mongoDbLocalPort = mongoDbProp.getLocalPort();
        mongoDbHost = mongoDbProp.getHost();
        mongoDbRemotePort = mongoDbProp.getRemotePort();
    }

    @PostConstruct
    public void startSSHTunnel() {
        if (!sshIsEnable) {
            return;
        }
        try {
            jsch = new JSch();

            session = jsch.getSession(sshUser, sshHost, sshPort);
            session.setPassword(sshPassword);
            session.setConfig("StrictHostKeyChecking", "no");
            logger.info("Conectando al servidor SSH {}:{} con usuario {}", sshHost, sshPort, sshUser);
            session.connect();
            logger.info("Conexión SSH establecida.");

            //MongoDb ssh connection
            int assignedPortMongoDb = session.setPortForwardingL(mongoDbLocalPort, mongoDbHost, mongoDbRemotePort);
            logger.info("Túnel SSH establecido: localhost:{} -> {}:{}", assignedPortMongoDb, mongoDbHost, mongoDbRemotePort);

        } catch (JSchException e) {
            logger.error("Error al iniciar el túnel SSH: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to start SSH tunnel", e);
        }
    }

    @PreDestroy
    public void stopSSHTunnel() {
        if (session != null && session.isConnected()) {
            session.disconnect();
            logger.info("SSH Session Closed");
        }
    }
}
