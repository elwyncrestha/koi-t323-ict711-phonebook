package exception;

public class MethodNotImplementedException extends Exception {

    public MethodNotImplementedException(String methodName) {
        super(String.format("%s method is not implemented.", methodName));
    }
}
