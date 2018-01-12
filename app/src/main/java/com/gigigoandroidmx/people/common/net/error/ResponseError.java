package com.gigigoandroidmx.people.common.net.error;

/**
 * @author Omar Bacilio - January 11, 2018
 * @version 0.0.1
 * @since 0.0.1
 */
public interface ResponseError {
    String getError();
    boolean hasErrorMessage();
}
