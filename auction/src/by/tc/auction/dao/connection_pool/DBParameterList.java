package by.tc.auction.dao.connection_pool;

/**
 * A list of connection pool parameters.
 * @author semenovich
 *
 */
public final class DBParameterList {
	
	private DBParameterList(){}

    static final String DATABASE_DRIVER = "config.driver";
    static final String DATABASE_URL = "config.url";
    static final String DATABASE_USER = "config.login";
    static final String DATABASE_PASSWORD = "config.password";
    static final String DATABASE_POOL_SIZE = "config.pool_size";
}
