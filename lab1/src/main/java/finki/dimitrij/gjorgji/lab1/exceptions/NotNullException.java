package finki.dimitrij.gjorgji.lab1.exceptions;

public class NotNullException extends RuntimeException {
    private static final String messageFormat = "Field cannot be null: %s";

    public NotNullException(String field) {
        super(String.format(messageFormat, field));
    }
}
