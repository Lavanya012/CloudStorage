package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private CredentialService credentialService;
    private UserService userService;
    private NoteService noteService;
    private FileService fileService;
    private EncryptionService encryptionService;

    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    public HomeController(CredentialService credentialService, UserService userService,
                          NoteService noteService, FileService fileService, EncryptionService encryptionService) {
        this.credentialService = credentialService;
        this.userService=userService;
        this.noteService=noteService;
        this.fileService = fileService;
        this.encryptionService=encryptionService;
    }

    @GetMapping
    public String getHome(Model model, Authentication authentication){
        Integer currentUserId = userService.getUser(authentication.getName()).getUserId();


        //Credentials
        List<Credentials> credentialsList=credentialService.getCredentialsForUser(currentUserId);

        Iterator<Credentials> iterator=credentialsList.iterator();
        while(iterator.hasNext()){
            Credentials cren=iterator.next();
            logger.error("username="+cren.getUrlUserName());
            logger.error("cryptedpassword="+cren.getUrlPassWord());
            logger.error("url="+cren.getUrl());
            logger.error("crenid="+cren.getCredentialId().toString());
            logger.error("userid="+cren.getUserId().toString());

        }

        model.addAttribute("encryptionService",encryptionService);
        model.addAttribute("credentials",credentialsList);

        //Notes

        List<Note> notesList= noteService.getNotesForUser(currentUserId);
        Iterator<Note> iterator1=notesList.iterator();
        while(iterator1.hasNext()){
            Note n1= iterator1.next();
            logger.error("at Home, from DB query noteid="+n1.getNoteid().toString());
        }

        model.addAttribute("noteslist",notesList);

        //Files
        List<File> fileList = fileService.getAllFilesForUser(currentUserId);

        model.addAttribute("fileList", fileList);

        return "home";
    }
}
