package org.neocities.api;

import java.nio.file.Path;

public class FileToUpload {

    private final String sourcePath;
    private final String targetPath;

    public FileToUpload(final String sourcePath, final String targetName) {
        this.sourcePath = sourcePath;
        this.targetPath = targetName;
    }

    String getSourcePath() {
        return sourcePath;
    }

    String getTargetPath() {
        return targetPath;
    }
}
