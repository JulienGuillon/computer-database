package com.excilys.computerdatabase.exception;

/**
 * @author Guillon Julien
 *
 *         24 févr. 2017
 */
public class PersistenceException extends RuntimeException {

    /**
     *
     */
    public PersistenceException() {
        super();
    }

    /**
     *
     * @param message :
     * @param cause :
     */
    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param message :
     */
    public PersistenceException(String message) {
        super(message);
    }

    /**
     *
     * @param cause :
     */
    public PersistenceException(Throwable cause) {
        super(cause);
    }

}
