package directory.content.task.model;

public class ErrorResponse extends CommonResponse {
    private String message;

    public ErrorResponse(String status, String message) {
        super(status);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
