package it.lastminute.sales.security.handler;

/**
 * Bean representing the generic error json returned by REST api.
 * 
 * @author cesare
 *
 */
public class JsonErrorMessage {

    private int statusCode;
    private String message;

    public JsonErrorMessage() {
        super();
    }

    public JsonErrorMessage(int statusCode, String message) {
        super();
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "JsonErrorMessage [statusCode=" + statusCode + ", message=" + message + "]";
    }
}
