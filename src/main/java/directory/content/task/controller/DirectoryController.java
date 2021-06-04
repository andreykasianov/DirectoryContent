package directory.content.task.controller;

import directory.content.task.model.CommonResponse;
import directory.content.task.model.DirectoryRequest;
import directory.content.task.service.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class DirectoryController {

    private DirectoryService directoryService;

    @Autowired
    public DirectoryController(final DirectoryService directoryService) {
        this.directoryService = directoryService;
    }

    @PostMapping(value = "/content", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public CommonResponse getDirectoryContent(@RequestBody DirectoryRequest directory) {
        return new CommonResponse(directoryService.getDirectoryContent(directory));
    }
}
