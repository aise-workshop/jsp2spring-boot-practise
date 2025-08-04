package hello;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class LoggingService {
    
    // 混合使用两种日志框架，用于演示 OpenRewrite 迁移
    private Log commonsLog = LogFactory.getLog(LoggingService.class);
    private static final Logger slf4jLogger = LoggerFactory.getLogger(LoggingService.class);
    
    private AtomicInteger requestCount = new AtomicInteger(0);
    
    public void logUserAction(String username, String action) {
        // Commons Logging 示例
        commonsLog.info("User " + username + " performed action: " + action);
        
        // SLF4J 示例 - 使用占位符
        slf4jLogger.info("User {} performed action: {}", username, action);
        
        int count = requestCount.incrementAndGet();
        slf4jLogger.debug("Request count incremented to: {}", count);
    }
    
    public void logError(String operation, Exception e) {
        // Commons Logging 错误日志
        commonsLog.error("Error during " + operation, e);
        
        // SLF4J 错误日志
        slf4jLogger.error("Error during operation: {}", operation, e);
        slf4jLogger.warn("Operation {} failed, but continuing", operation);
    }
    
    public void logPerformance(String operation, long duration) {
        // Commons Logging 性能日志
        commonsLog.info("Operation " + operation + " took " + duration + "ms");
        
        // SLF4J 性能日志
        slf4jLogger.info("Operation {} completed in {}ms", operation, duration);
        
        if (duration > 1000) {
            slf4jLogger.warn("Slow operation detected: {} took {}ms", operation, duration);
        }
    }
    
    public void logDebugInfo(String component, String details) {
        // Commons Logging 调试日志
        commonsLog.debug("Debug info for " + component + ": " + details);
        
        // SLF4J 调试日志
        slf4jLogger.debug("Debug info for component {}: {}", component, details);
        slf4jLogger.trace("Detailed trace for {}: {}", component, details);
    }
    
    public void logSecurityEvent(String event, String user) {
        // Commons Logging 安全日志
        commonsLog.warn("Security event: " + event + " by user: " + user);
        
        // SLF4J 安全日志
        slf4jLogger.warn("Security event: {} by user: {}", event, user);
        slf4jLogger.info("Security audit: {} - {}", event, user);
    }
    
    public void logWithDifferentLevels() {
        // 演示不同日志级别
        slf4jLogger.trace("This is a TRACE message");
        slf4jLogger.debug("This is a DEBUG message");
        slf4jLogger.info("This is an INFO message");
        slf4jLogger.warn("This is a WARN message");
        slf4jLogger.error("This is an ERROR message");
        
        // Commons Logging 不同级别
        commonsLog.debug("Commons DEBUG message");
        commonsLog.info("Commons INFO message");
        commonsLog.warn("Commons WARN message");
        commonsLog.error("Commons ERROR message");
    }
} 