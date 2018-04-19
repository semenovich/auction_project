package by.tc.auction.controller.listener.logger;

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

public class LoggerInitializeListener implements ServletContextListener {
    private static final String LOG_CONFIG_PATH = "log4j-config-location";
    private static final String EMPTY_STRING = "";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        String log4jConfigFile = servletContext.getInitParameter(LOG_CONFIG_PATH);
        String fullPath = servletContext.getRealPath(EMPTY_STRING) + File.separator + log4jConfigFile;

        PropertyConfigurator.configure(fullPath);
    }
}
