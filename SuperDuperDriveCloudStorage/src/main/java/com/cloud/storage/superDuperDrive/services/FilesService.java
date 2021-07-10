package com.cloud.storage.superDuperDrive.services;

import com.cloud.storage.superDuperDrive.mapper.FilesMapper;
import com.cloud.storage.superDuperDrive.model.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FilesService {
    private FilesMapper filesMapper;

    @Autowired
    public FilesService(FilesMapper filesMapper) {
        this.filesMapper = filesMapper;
    }

    public List<Files> getFilesByUserId(Integer userId) {
        return filesMapper.findFilesByUserId(userId);
    }

    public Files getFilesByFileName(String fileName) {
        return  filesMapper.findFilesByFileName(fileName);
    }

    public int removeFile(int fileId) {
        return filesMapper.deleteFile(fileId);
    }

    public int uploadFile(MultipartFile fileToUpload, int userId) throws IOException {
        Files file = new Files();

        file.setContentType(fileToUpload.getContentType());
        file.setFileData(fileToUpload.getBytes());
        file.setFileName(fileToUpload.getOriginalFilename());
        file.setFileSize(Long.toString(fileToUpload.getSize()));
        file.setUserId(userId);

        return filesMapper.insertFile(file);
    }

    public Files getFileById(Integer fileId) {
        return filesMapper.findFileById(fileId);
    }

}
