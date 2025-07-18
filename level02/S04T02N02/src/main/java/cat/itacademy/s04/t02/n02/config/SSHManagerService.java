package cat.itacademy.s04.t02.n02.config;

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
    private final MySqlProperties mySqlProp;

    // SSH connection
    private final boolean sshIsEnable;
    private final String sshHost;
    private final int sshPort;
    private final String sshUser;
    private final String sshPassword;
    private final String sshPrivateKeyPath;
    private final String sshPrivateKeyPassword;

    // MySql connection
    private final int mysqlLocalPort;
    private final String mysqlHost;
    private final int mysqlRemotePort;

    private Session session;
    private JSch jsch;

    public SSHManagerService(SSHProperties sshProperties, MySqlProperties mySqlProperties) {
        this.sshProp = sshProperties;
        this.mySqlProp = mySqlProperties;

        // SSH connection
        sshIsEnable = sshProp.isEnable();
        sshHost = sshProp.getHost();
        sshPort = sshProp.getHostPort();
        sshUser = sshProp.getUser();
        sshPassword = sshProp.getPassword();
        sshPrivateKeyPath = sshProp.getPrivateKeyPath();
        sshPrivateKeyPassword = sshProp.getPrivateKeyPassword();

        // MySql connection
        mysqlLocalPort = mySqlProp.getLocalPort();
        mysqlHost = mySqlProp.getHost();
        mysqlRemotePort = mySqlProp.getRemotePort();
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

            int assignedPort = session.setPortForwardingL(mysqlLocalPort, mysqlHost, mysqlRemotePort);
            logger.info("Túnel SSH establecido: localhost:{} -> {}:{}", assignedPort, mysqlHost, mysqlRemotePort);

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
