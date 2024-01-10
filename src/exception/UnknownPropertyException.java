package exception;

public class UnknownPropertyException extends Exception {

    public UnknownPropertyException(String propertyName) {
        super(String.format("%s property is not supported.", propertyName));
    }
}
