package by.tc.auction.controller.command.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import by.tc.auction.controller.command.ServletCommand;

/**
 * A class is used to parse a XML file with commands ("/auction/src/resources/commands/commands.xml").
 * @author semenovich
 *
 */
public class CommandParser {
	
	private static final String SOURCE_PATH = "/home/semenovich/eclipse/JavaEE/auction/" + 
												"src/resources/commands/commands.xml";
	
	private static final File SOURCE = new File(SOURCE_PATH);
	
	private static final Logger logger = Logger.getLogger(CommandParser.class);
	
	private static final CommandParser instance = new CommandParser();
	
	private CommandParser() {}
	
	public static CommandParser getInstance() {
		return instance;
	}
	
	/**
	 * Parses a XML file ("/auction/src/resources/commands/commands.xml") with commands.
	 * @return A map with commands. Keys of commands according to names of commands in "/auction/src/resources/commands/commands.xml". 
	 * @throws Exception - error with the parser. 
	 */
	public HashMap<String, ServletCommand> parse() throws Exception {
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		SAXHandler saxHandler = new SAXHandler();
		try {
			SAXParser saxParser = parserFactory.newSAXParser();
			saxParser.parse(SOURCE, saxHandler);
			return saxHandler.getCommands();
		} catch (SAXException | IOException | ParserConfigurationException e) {
			logger.fatal("Fatal error in CommandParser", e);
			throw e;
		}
	}
}
