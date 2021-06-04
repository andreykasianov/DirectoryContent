package directory.content.task.service;

import directory.content.task.model.DirectoryRequest;

import java.util.List;

public interface DirectoryService {

    List<String> getDirectoryContent(final DirectoryRequest directory);
}
