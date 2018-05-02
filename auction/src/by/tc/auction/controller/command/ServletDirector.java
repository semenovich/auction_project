package by.tc.auction.controller.command;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import by.tc.auction.controller.command.util.CommandParser;

/**
 * A class is used to get commands to controller from "/auction/src/resources/commands/commands.xml"   
 * @author semenovich
 *
 */
public final class ServletDirector {

	private static final Logger logger = Logger.getLogger(ServletDirector.class);

	private static ServletDirector instance;

    private static Lock lock;
    private static AtomicBoolean isInitialized;

	private Map <String, ServletCommand> commands; 

	private static final CommandParser commandParser = CommandParser.getInstance();
	
	static{
        lock = new ReentrantLock();
        isInitialized = new AtomicBoolean();
    }
	
	private ServletDirector() {
		try {
			commands = commandParser.parse();
		} catch (Exception e) {
			logger.fatal("FatalError in ServletDirector", e);
		}
	}

	/**
	 * Returns the instance of the ServletDirector.
	 * @return the instance of the ServletDirector.
	 */
	public static ServletDirector getInstance(){
		if(!isInitialized.get()){
            lock.lock();
            try{
                if(!isInitialized.get()){
                    instance = new ServletDirector();
                    isInitialized.set(true);
                }
            }finally {
                lock.unlock();
            }
        }
		return instance;
	}
	
	/**
	 * Returns the command.
	 * @param command - a command name in "/auction/src/resources/commands/commands.xml".
	 * @return the command.
	 */
	public ServletCommand getCommand(String command) {
		return commands.get(command);
	}
}
