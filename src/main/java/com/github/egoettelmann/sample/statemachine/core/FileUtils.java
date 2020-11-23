package com.github.egoettelmann.sample.statemachine.core;

import java.io.File;
import java.util.UUID;

public class FileUtils {

    /**
     * Generates a file reference with a random name in the given path.
     * Creates folder structure if necessary.
     *
     * @param path the folder path
     * @param extension the file extension
     * @return the file reference
     */
    public static File generateFile(String path, String extension) {
        String fileName = path + UUID.randomUUID().toString() + "." + extension;
        File file = new File(fileName);
        file.getParentFile().mkdirs();
        return file;
    }

}
