package hello;

import org.apache.catalina.session.FileStore;
import org.apache.catalina.session.PersistentManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.util.Arrays;

@SpringBootApplication
public class Application {
    private Log log = LogFactory.getLog(Application.class);
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return factory -> {
            TomcatEmbeddedServletContainerFactory containerFactory = (TomcatEmbeddedServletContainerFactory) factory;
            containerFactory.setTomcatContextCustomizers(Arrays.asList(context -> {
                final PersistentManager persistentManager = new PersistentManager();
                final FileStore store = new FileStore();

                final String sessionDirectory = makeSessionDirectory();
                log.info("Writing sessions to " + sessionDirectory);
                logger.info("Session directory configured: {}", sessionDirectory);
                store.setDirectory(sessionDirectory);

                persistentManager.setStore(store);
                context.setManager(persistentManager);
                logger.debug("Persistent manager configured with file store");
            }));
        };
    }

    private String makeSessionDirectory() {
        final String cwd = System.getProperty("user.dir");
        String sessionDir = cwd + File.separator + "sessions";
        logger.trace("Creating session directory: {}", sessionDir);
        return sessionDir;
    }

    public static void main(String[] args) {
        logger.info("Starting Spring Boot application...");
        SpringApplication.run(Application.class, args);
        logger.info("Spring Boot application started successfully");
    }
}
