package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class CredentialController {
    private CredentialService credentialService;
    private UserService userService;
    private AuthenticationService authenticationService;

    private Logger logger = LoggerFactory.getLogger(CredentialController.class);

    @Autowired
    public CredentialController(CredentialService credentialService, UserService userService, AuthenticationService authenticationService) {
        this.credentialService = credentialService;
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("home/credentials")
    public String createCredential(@ModelAttribute Credentials credential, RedirectAttributes redirectAttributes) {
        String credential_err = null;
        String credential_ok = null;
        String userName = authenticationService.getUserName();
        Integer credId = credential.getCredentialId();

        if (userName == null)
            credential_err = "Invalid session";

        int userId = -1;
        if (credential_err == null) {
            User user = userService.getUser(userName);
            if (user != null)
                credential.setUserId(user.getUserId());
            else
                credential_err = "Invalid Session";
        }

        if (credential_err == null) {//create new credential
            if (credId == null) {
                credentialService.encryptPassword(credential);
                int rowAdded = credentialService.createCredential(credential);
                if (rowAdded < 0) {
                    credential_err = "Error while creating credentials";
                } else {
                    credId = credentialService.getLastCredentialId();//newly inserted is always the last one
                    credential_ok = "credentials creation success";
                }
            } else {

                credentialService.updateCredentialWithKey(credential);
                credentialService.encryptPassword(credential);

                int rowUpdated = credentialService.updateCredential(credential);
                if (rowUpdated < 0)
                    credential_err = "Error while updating credentials";
                else
                    credential_ok = "credentials update success";
            }
        }


        if (credential_err == null) {
            redirectAttributes.addAttribute("opCredOk", true);
            redirectAttributes.addAttribute("opCredMsg", credential_ok + " -ID:" + credId.toString());
        } else {
            redirectAttributes.addAttribute("opCredNotOk", true);
            redirectAttributes.addAttribute("opCredMsg", credential_err + " -ID:" + credId.toString());
        }

        return ("redirect:/home");
    }


    @GetMapping("/home/credentials/delete/{credentialId}")
    public String deleteCredential(@PathVariable("credentialId") Integer credentialId,
                                   RedirectAttributes redirectAttributes) {

        String credential_err = null;
        String credential_ok = null;

        int rowDeleted = credentialService.deleteCredential(credentialId);
        if (rowDeleted < 0)
            credential_err = "Credential deletion failed";
        else
            credential_ok = "Deleting credential is successful";

        if (credential_err == null) {
            redirectAttributes.addAttribute("opCredOk", true);
            redirectAttributes.addAttribute("opCredMsg", credential_ok + " -ID:" + credentialId.toString());
        } else {
            redirectAttributes.addAttribute("opCredNotOk", true);
            redirectAttributes.addAttribute("opCredMsg", credential_err + " -ID:" + credentialId.toString());
        }

        return ("redirect:/home");
    }
}
