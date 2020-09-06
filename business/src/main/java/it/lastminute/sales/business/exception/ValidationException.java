package it.lastminute.sales.business.exception;

import java.util.Set;

public final class ValidationException extends RuntimeException {
    private static final long serialVersionUID = -8047576519669710938L;

    private Set constraintViolations;

    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(Set constraintViolations) {
        super();
        this.constraintViolations = constraintViolations;
    }

    /**
     * constraintViolations getter.
     * 
     * @return the constraintViolations
     */
    public Set getConstraintViolations() {
        return constraintViolations;
    }

    /**
     * constraintViolations setter.
     * 
     * @param constraintViolations
     *            the constraintViolations to set
     */
    public void setConstraintViolations(Set constraintViolations) {
        this.constraintViolations = constraintViolations;
    }

}
