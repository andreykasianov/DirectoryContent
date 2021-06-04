package directory.content.task.model;

import java.io.Serializable;

public class DirectoryRequest implements Serializable {
    private String path;

    public DirectoryRequest() {
    }

    public DirectoryRequest(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
