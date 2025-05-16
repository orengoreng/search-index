package com.sample.countwords.service;

import com.sample.countwords.exception.SearchIndexException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

    private final Logger logger = LoggerFactory.getLogger(FileService.class);

    private final static String FILENAME = "content.txt";
    private final static String TXT_EXTENSION = "txt";

    @Value("${storage.location}")
    private String dir;

    private final ResourceLoader resourceLoader;

    /**
     * Save uploaded file to directory, only txt file can be read
     * @param file txt file
     * @throws SearchIndexException exception
     */
    public void store(MultipartFile file) throws SearchIndexException {
        try {
            if (file.isEmpty()) {
                throw new SearchIndexException("Failed to store empty file.");
            }
            if (!TXT_EXTENSION.equals(FilenameUtils.getExtension(file.getOriginalFilename()))) {
                throw new SearchIndexException(String.format("%s cannot be processed, only .txt file is accepted.", file.getOriginalFilename()));
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, Paths.get( dir, "/", FILENAME),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (SearchIndexException | IOException e) {
            throw new SearchIndexException("Failed to store file.", e);
        }
    }

    /**
     * Return file content to string
     * @return string data
     */
    public String loadFile()  {
        try {
            Path path = Paths.get(  dir, "/",  FILENAME);
            return Files.readString(path);
        } catch (IOException e) {
            throw new SearchIndexException("Failed to load file, upload a file first.", e);
        }
    }

}
