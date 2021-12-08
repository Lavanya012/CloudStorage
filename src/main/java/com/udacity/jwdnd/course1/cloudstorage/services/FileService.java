package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FileService {

    private FileMapper fileMapper;
    private Logger logger = LoggerFactory.getLogger(FileService.class);

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public Integer uploadFile(File file) {
        logger.info("FileService: Inside upload file");
        return fileMapper.insertFile(file);
    }

    public List<File> getAllFilesForUser(Integer userId) {
        return fileMapper.selectFilesForUser(userId);
    }


    public Integer deleteFile(String fileName) {
        return fileMapper.deleteFile(fileName);
    }


    public boolean isFileDuplicate(Integer userId, String fileName) {
        List<String> fileNameList = fileMapper.getAllFileNamesForUser(userId);
        return fileNameList.contains(fileName);
    }

    public File getFileByNameForUser(Integer userId, String fileName) {
        return fileMapper.getFileByNameForUser(userId, fileName);
    }
}
