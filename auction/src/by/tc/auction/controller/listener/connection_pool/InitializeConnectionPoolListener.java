package by.tc.auction.controller.listener.connection_pool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.tc.auction.dao.connection_pool.ConnectionPool;


public class InitializeConnectionPoolListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool.getInstance();
    }
}
