package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@RequestMapping
public class NoteController {
    private NoteService noteService;
    private UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping("home/note")
    public String notePostRequest(@ModelAttribute Note note, RedirectAttributes redirectAttributes, Authentication authentication) {
        Logger logger = LoggerFactory.getLogger(NoteMapper.class);
        String noteError = null;
        String noteSuccess =null;
        Integer noteId=null;
        try {
            Integer userId = userService.getUser(authentication.getName()).getUserId();
            note.setUserid(userId);

            noteId= note.getNoteid();

            logger.error("noteId="+note.getNoteid());
            if (noteId == null) {//new note
                Integer rowAdded = noteService.insertNote(note);
                logger.error("note row added="+rowAdded.toString());

                if (rowAdded < 0) {
                    noteError = "Getting error in creating Note";

                }
                else {
                    noteSuccess="Note added succesfully";
                    noteId=noteService.getLastNoteId();//newly inserted Note definetly has the last noteid;
                }
            } else {//edit note
                logger.error("updating note");
                Integer rowUpdated = noteService.updateNote(note);
                if(rowUpdated<0){
                    noteError = "Error in creating Notes";
                }
                else {
                    noteSuccess="Noted updated succesfully";
                    //noteId=note.getNoteid(); //for edit, just use the input NoteId;
                }

            }
        }catch(Exception a){
            noteError="Invalid session";
            logger.error(a.toString());
        }

        if(noteError==null) {redirectAttributes.addAttribute("opNoteOk",true); redirectAttributes.addAttribute("opNoteMsg",noteSuccess+" -ID:"+noteId.toString());}
        else {redirectAttributes.addAttribute("opNoteNotOk",true);redirectAttributes.addAttribute("opNoteMsg",noteError+" -ID:"+noteId.toString());}


        return("redirect:/home");
    }

    @GetMapping("/home/note/delete/{noteId}")
    public String deleteNote(@PathVariable("noteId") Integer noteId, RedirectAttributes redirectAttributes){
        String noteError=null;
        String noteSuccess=null;
        int rowDeleted=noteService.deleteNote(noteId);
        if(rowDeleted<0)
            noteError="Error in deleting Notes";
        else
            noteSuccess="Note deleted successfully";

        //handling msg (success or failure) attributes
        if(noteError==null) {redirectAttributes.addAttribute("opNoteOk",true); redirectAttributes.addAttribute("opNoteMsg",noteSuccess+" -ID:"+noteId.toString());}
        else {redirectAttributes.addAttribute("opNoteNotOk",true);redirectAttributes.addAttribute("opNoteMsg",noteError+" -ID:"+noteId.toString());}

        return("redirect:/home");
    }

}
