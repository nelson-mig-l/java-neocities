package org.neocities.utils;

import org.neocities.api.FileToUpload;

import java.nio.file.Path;

public class FileToUploadHelper {

    public static FileToUpload uploadFileToRoot(final String sourcePath) {
        final Path targetPath = Path.of(sourcePath).getFileName();
        return new FileToUpload(sourcePath, targetPath.toString());
    }

    public static FileToUpload uploadFileToDirectory(final String sourcePath, final String targetDirectory) {
        final Path fileName = Path.of(sourcePath).getFileName();
        final Path targetPath = Path.of(targetDirectory).resolve(fileName);
        return new FileToUpload(sourcePath, targetPath.toString());
    }

    public static FileToUpload uploadFileToSpecificPath(final String sourcePath, final String targetPath) {
        return new FileToUpload(sourcePath, targetPath);
    }

}
