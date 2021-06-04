package directory.content.task.service;

import directory.content.task.config.exception.NotSupportedPathException;
import directory.content.task.model.DirectoryRequest;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DirectoryServiceImpl implements DirectoryService {

    private static final String PATH_NULL_ERROR_MESSAGE = "Path can not be null.";

    @Override
    public List<String> getDirectoryContent(final DirectoryRequest directory) {
        final String path = directory.getPath();

        validateNotNull(path);

        final List<String> files;
        final File file = new File(path);

        if (file.exists() && file.isDirectory()) {
            files = Arrays.stream(file.listFiles()).map(File::getName).collect(Collectors.toList());
        } else {
            throw new NotSupportedPathException();
        }
        return files;
    }

    private void validateNotNull(final String path) {
        if (path == null) throw new NotSupportedPathException(PATH_NULL_ERROR_MESSAGE);
    }
}
