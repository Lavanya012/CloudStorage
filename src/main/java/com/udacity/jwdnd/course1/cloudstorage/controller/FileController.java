package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

//import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.InputStream;



@Controller
public class FileController {
    private UserService userService;
    private FileService fileService;

    public FileController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    private Logger logger = LoggerFactory.getLogger(FileController.class);

    @PostMapping("/home/fileupload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile multipartFile, RedirectAttributes redirectAttributes,
                             Authentication authentication){

        logger.info("fileuploadController, upload file ");
        String file_err=null;
        final String file_ok="File uploaded Succeesfully";
        byte[] fb=null;
        String fileName=null;

        try {
            Integer userId= userService.getUser(authentication.getName()).getUserId();
            fileName=multipartFile.getOriginalFilename();
            String contentType= multipartFile.getContentType();
            Long fileSize=multipartFile.getSize();

            //First test if user really selected a file
            logger.error("fileName="+fileName);
            if(fileName.length()==0) {
                file_err="Please select a File before uploading";
                Exception a= new Exception("No file was selected");
                throw(a);
            }

            if((multipartFile.getSize() > 5242880)) {
                throw new MaxUploadSizeExceededException(multipartFile.getSize());
            }

            //Second test if the file (by fileName) is already in DB
            if(fileService.isFileDuplicate(userId,fileName)){
                file_err="File Already Exists";
                Exception a= new Exception("Duplicated file name");
                throw(a);
            }

            InputStream fis = multipartFile.getInputStream();
            fb=new byte[fis.available()];
            fis.read(fb);
            fis.close();

            //manually create the fileModel POJO with info from multipart file
            File file =new File(null,fileName,contentType,fileSize.toString(),userId,fb);
            fileService.uploadFile(file);
        }catch (MaxUploadSizeExceededException a){
            logger.error("file size limit exceed expception captured");
            logger.error(a.toString());
            a.printStackTrace();
            file_err="File size exceeded";
        }
        catch (Exception a){
            if(file_err==null) file_err="Unexpected error";
            logger.error(a.toString());
            a.printStackTrace();
        }

        //handling msg (success or failure) attributes
        if(file_err==null) {redirectAttributes.addAttribute("opok",true); redirectAttributes.addAttribute("opmsg",file_ok+": "+fileName);}
        else {redirectAttributes.addAttribute("opnotok",true);redirectAttributes.addAttribute("opmsg",file_err+": "+fileName);}

        return ("redirect:/home");
    }

    @GetMapping("/home/file/delete/{filename}")
    public String deleteFile(@PathVariable("filename") String fileName,Authentication authentication,RedirectAttributes redirectAttributes){
        String file_err=null;
        String file_ok="File Deleted Succesfully";
        logger.info("File delete controller");
        try{
            int rowDeleted= fileService.deleteFile(fileName);
            if(rowDeleted<0) file_err="Deletion of file is unsuccesful";

        }catch(Exception a){
            if(file_err==null) file_err="Unexpected error";
           logger.error(a.toString());
        }

        //handling msg (success or failure) attributes
        if(file_err==null) {redirectAttributes.addAttribute("opok",true); redirectAttributes.addAttribute("opmsg",file_ok+": "+fileName);}
        else {redirectAttributes.addAttribute("opnotok",true);redirectAttributes.addAttribute("opmsg",file_err+": "+fileName);}

        return ("redirect:/home");
    }

    @GetMapping("home/files/download/{filename}")
    public ResponseEntity downloadFile(@PathVariable("filename") String fileName, Authentication authentication, RedirectAttributes redirectAttributes){

        Integer userId= userService.getUser(authentication.getName()).getUserId();
        File dfile = null;
        try {
              dfile = fileService.getFileByNameForUser(userId,fileName);
        }catch (Exception a){
            logger.error(a.toString());
            return ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dfile.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+fileName+"\"")
                .body(dfile.getFileData());

    }
}
