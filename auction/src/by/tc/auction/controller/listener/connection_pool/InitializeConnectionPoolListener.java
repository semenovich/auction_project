package by.tc.auction.controller.listener.connection_pool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.tc.auction.dao.connection_pool.ConnectionPool;

/**
 * A class is used to initialize a connection pool.
 * @author semenovich
 *
 */
public class InitializeConnectionPoolListener implements ServletContextListener {

	/**
	 * Initializes a connection pool. 
	 */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool.getInstance();
    }
}
