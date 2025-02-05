package com.media.service;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class FileService {

    private final GridFsTemplate gridFsTemplate;

    public FileService(GridFsTemplate gridFsTemplate) {
        this.gridFsTemplate = gridFsTemplate;
    }

    public String getFileUrl(String fileId) {
        return "/api/files/" + fileId; // Create a URL to access the file
    }
    public byte[] getFile(String fileId) {
        // Locate the file in GridFS
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(fileId)));
        if (gridFSFile == null) {
            throw new RuntimeException("File with ID " + fileId + " not found in GridFS.");
        }

        // Retrieve the file content
        try (InputStream inputStream = gridFsTemplate.getResource(gridFSFile).getInputStream()) {
            return IOUtils.toByteArray(inputStream); // Convert InputStream to byte array
        } catch (IOException e) {
            throw new RuntimeException("Error reading file with ID " + fileId + " from GridFS.", e);
        }
    }
}
