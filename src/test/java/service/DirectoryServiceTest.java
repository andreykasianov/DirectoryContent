package service;

import directory.content.task.config.exception.NotSupportedPathException;
import directory.content.task.model.DirectoryRequest;
import directory.content.task.service.DirectoryService;
import directory.content.task.service.DirectoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DirectoryServiceTest {

    @TempDir
    File tempDir;
    private DirectoryService directoryService = new DirectoryServiceImpl();

    @Test
    void getDirectoryContent() {
        final File directory = createEmptyDirectory();
        final File notIncludedFile = createTxtFile(directory);
        final File txtFile = createTxtFile();
        final List<String> fileNames = directoryService.getDirectoryContent(new DirectoryRequest(tempDir.getPath()));
        assertAll(
                () -> assertEquals(2, fileNames.size()),
                () -> assertTrue(fileNames.contains(txtFile.getName())),
                () -> assertTrue(fileNames.contains(directory.getName())),
                () -> assertFalse(fileNames.contains(notIncludedFile.getName())),
                () -> assertEquals(1, directory.listFiles().length));
    }

    @Test
    void getEmptyDirectoryContent() {
        final List<String> fileNames = directoryService.getDirectoryContent(new DirectoryRequest(tempDir.getPath()));
        assertEquals(0, fileNames.size());
    }

    @Test
    void pathCanNotBeNull() {
        final String path = null;
        NotSupportedPathException thrown = assertThrows(NotSupportedPathException.class, () -> directoryService.getDirectoryContent(new DirectoryRequest(path)));
        assertEquals("Path can not be null.", thrown.getMessage());
    }

    @Test
    void notExistingPath() {
        final String randomPath = randomAlphabetic(5);
        NotSupportedPathException thrown = assertThrows(NotSupportedPathException.class, () -> directoryService.getDirectoryContent(new DirectoryRequest(randomPath)));
        assertEquals("Cannot find the directory. Path must be relative. Make sure the path is correct.", thrown.getMessage());
    }

    @Test
    void absolutePathToTxtFile() {
        final File letters = createTxtFile();
        NotSupportedPathException thrown = assertThrows(NotSupportedPathException.class, () -> directoryService.getDirectoryContent(new DirectoryRequest(letters.getPath())));
        assertEquals("Cannot find the directory. Path must be relative. Make sure the path is correct.", thrown.getMessage());
    }

    private File createTxtFile() {
        return createTxtFile(tempDir);
    }

    private File createTxtFile(final File baseDir) {
        final File file = new File(baseDir, randomAlphabetic(5) + ".txt");
        List<String> lines = Arrays.asList("a", "b", "c");
        try {
            Files.write(file.toPath(), lines);
        } catch (IOException ioe) {
            System.err.println("error write data to file");
        }
        return file;
    }

    private File createEmptyDirectory() {
        final File file = new File(tempDir, randomAlphabetic(5));
        file.mkdirs();
        return file;
    }
}
