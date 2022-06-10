package com.console.draw.exceptions;

import java.io.Serializable;

/**
 * 
 * @author Sujith Suresh
 *
 */
public class InvalidParamsException extends RuntimeException implements Serializable{


	private static final long serialVersionUID = -1928575585755061436L;
	private String helpMsg;

	public InvalidParamsException(String message, String helpMsg) {
        super(message);
        this.helpMsg = helpMsg;
    }

	public String getHelpMessage() {
		return helpMsg;
	}

}
