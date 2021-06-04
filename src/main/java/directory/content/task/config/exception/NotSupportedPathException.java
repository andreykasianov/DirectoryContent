package directory.content.task.config.exception;

public class NotSupportedPathException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Cannot find the directory. Path must be relative. Make sure the path is correct.";

    public NotSupportedPathException() {
        super(DEFAULT_MESSAGE);
    }

    public NotSupportedPathException(final String message) {
        super(message);
    }
}
