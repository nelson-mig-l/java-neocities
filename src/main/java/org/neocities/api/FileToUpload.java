package org.neocities.api;

public class FileToUpload {

    private static final char UNIX_SEPARATOR = '/';
    private static final char WINDOWS_SEPARATOR = '\\';

    private final String sourcePath;
    private final String targetPath;

    public FileToUpload(final String sourcePath, final String targetPath) {
        this.sourcePath = separatorsToUnix(sourcePath);
        this.targetPath = separatorsToUnix(targetPath);
    }

    String getSourcePath() {
        return sourcePath;
    }

    String getTargetPath() {
        return targetPath;
    }

    private String separatorsToUnix(final String path) {
        return path.replace(WINDOWS_SEPARATOR, UNIX_SEPARATOR);
    }
}
