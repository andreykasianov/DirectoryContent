package directory.content.task.model;

import java.io.Serializable;
import java.util.List;

import static directory.content.task.model.Status.SUCCESS;

public class CommonResponse implements Serializable {
    private List<String> entries;
    private String status = SUCCESS.getStatus();

    public CommonResponse(List<String> entries) {
        this.entries = entries;
    }

    public CommonResponse(String status) {
        this.status = status;
    }

    public CommonResponse(List<String> entries, String status) {
        this.entries = entries;
        this.status = status;
    }

    public List<String> getEntries() {
        return entries;
    }

    public void setEntries(List<String> entries) {
        this.entries = entries;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
