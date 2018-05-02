package by.tc.auction.controller.command.util;

import org.xml.sax.helpers.DefaultHandler;

import by.tc.auction.controller.command.ServletCommand;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

class SAXHandler extends DefaultHandler {
    
	private static final String OPEN_TAG = "<";
	
	private static final String COMMANDS = "commands";
	private static final String COMMAND = "command";
	private static final String COMMAND_NAME = "name";
	private static final String COMMAND_CLASS = "class";
	
	private HashMap<String, ServletCommand> commands;
	private String currentCommandName;
	private ServletCommand currentCommand;
	private String currentElement;
	
	private static final Logger logger = Logger.getLogger(SAXHandler.class);
	
	public HashMap<String, ServletCommand> getCommands(){
		return commands;
	}
	
	@Override
	public void startDocument() throws SAXException {
		return;
	}

	@Override
	public void endDocument() throws SAXException {
		return;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		currentElement = qName;
		if (currentElement.equals(COMMANDS)) {
			commands = new HashMap<>();
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals(COMMAND)) {
			commands.put(currentCommandName, currentCommand);
		}
		currentElement = null;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		try {
			String text = new String(ch, start, length);
			if (text.contains(OPEN_TAG) || currentElement == null) {
				return;
			}
			if (currentElement.equals(COMMAND_NAME)) {
				currentCommandName = text;;
			}
			if (currentElement.equals(COMMAND_CLASS)) {
				currentCommand = (ServletCommand) Class.forName(text).newInstance();
			} 		
		} catch (InstantiationException e) {
			logger.fatal("Fatal error in SAXHandler", e);
		} catch (IllegalAccessException e) {
			logger.fatal("Fatal error in SAXHandler", e);
		} catch (ClassNotFoundException e) {
			logger.fatal("ClassNotFoundException in SAXHandler", e);
		}

	}
}
