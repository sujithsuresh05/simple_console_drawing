package com.console.draw.exceptions;

import java.io.Serializable;

/**
 * 
 * @author Sujith Suresh
 *
 */
public class IllegalCommandException extends RuntimeException implements Serializable{
	
	private static final long serialVersionUID = 5547650055526342525L;
	private String helpMsg;
	
	public IllegalCommandException(String message, String helpMsg) {
        super(message);
        this.helpMsg = helpMsg;
    }

    public String getHelpMessage() {
        return helpMsg;
    }

}
