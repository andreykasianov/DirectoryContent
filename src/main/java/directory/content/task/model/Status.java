package directory.content.task.model;

public enum Status {

    SUCCESS("success"),
    ERROR("error");

    private String status;

    Status(final String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
