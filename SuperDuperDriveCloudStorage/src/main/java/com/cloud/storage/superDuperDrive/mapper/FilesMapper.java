package com.cloud.storage.superDuperDrive.mapper;

import com.cloud.storage.superDuperDrive.model.Files;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FilesMapper {
    @Select("SELECT * FROM FILES WHERE fileid = #{fileId}")
    Files findFileById(int fileId);

    @Select("SELECT * FROM FILES WHERE filename = #{fileName}")
    Files findFilesByFileName(String fileName);

    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    List<Files> findFilesByUserId(int userId);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, filedata, userid) VALUES (#{fileName}, #{contentType}, #{fileSize}, #{fileData}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(Files file);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileId}")
    int deleteFile(int fileId);
}
