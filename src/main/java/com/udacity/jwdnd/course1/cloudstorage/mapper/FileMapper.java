package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Insert("INSERT INTO FILES(filename,contenttype,filesize,userid,filedata) " +
            "VALUES(#{fileName},#{contentType},#{fileSize},#{userId},#{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    Integer insertFile(File file);

    @Select("SELECT * FROM FILES WHERE userid=#{userId}")
    List<File> selectFilesForUser(Integer userId);


    @Delete("DELETE FROM FILES WHERE filename=#{fileName}")
    Integer deleteFile(String fileName);

    @Select("SELECT filename FROM FILES WHERE userid=#{userId}")
    List<String> getAllFileNamesForUser(Integer userId);

    @Select("SELECT * FROM FILES WHERE filename=#{fileName} and userid=#{userId} ")
    File getFileByNameForUser(Integer userId, String fileName);

}
