package com.console.draw.exceptions;

import java.io.Serializable;

/**
 * 
 * @author Sujith Suresh
 *
 */
public class InvalidEntityException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 7383552140557755643L;

	public InvalidEntityException(String message) {
		super(message);
	}
}
