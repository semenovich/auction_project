package by.tc.auction.dao.connection_pool;

import java.util.ResourceBundle;

public class DBResourceManager {
    private static final String CONFIG_SQL_FILE = "resources/database/config";

    private static final DBResourceManager instance = new DBResourceManager();

    private ResourceBundle bundle = ResourceBundle.getBundle(CONFIG_SQL_FILE);

    public static DBResourceManager getInstance() {
        return instance;
    }

    public String getValue(String key){
        return bundle.getString(key);
    }
}
