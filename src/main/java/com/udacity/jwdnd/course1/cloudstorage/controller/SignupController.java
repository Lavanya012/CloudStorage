package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.HashService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Base64;

import static com.udacity.jwdnd.course1.cloudstorage.constant.ConstantMsgs.*;


@Controller
@RequestMapping("/signup")
public class SignupController {

    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(SignupController.class);

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showSignUpPage(@ModelAttribute("User") User user,  Model model){
        return "signup";
    }

    @PostMapping
    public String signUpNewUser(@ModelAttribute("User") User user, Model model, RedirectAttributes redirectAttributes){
        logger.error("---Starting signupNewUser---");
        logger.error("Before Hash, userName="+user.getUserName()+" passwd="+user.getPassWord()+" salt="+user.getSalt());
        logger.error("FirstName and Last name are"+user.getFirstName()+" "+user.getLastName());
        userService.hashUserPassword(user); //user object will be updated with hashed passwd and salt value
        logger.error("After Hash, userName="+user.getUserName()+" passwd="+user.getPassWord()+" salt="+user.getSalt());


        String signup_err = null;
        if(!userService.isUsernameAvailable(user.getUserName()))
            signup_err=SIGNUP_USER_EXISTING_ERR;

        if(signup_err==null){
           int rowAdded=  userService.insertUser(user);
           if(rowAdded<0)
               signup_err =SIGNUP_ERR;
        }

        if(signup_err==null){
            redirectAttributes.addAttribute("isSuccess",true);
            redirectAttributes.addAttribute("signupMsg",SIGNUP_SUCCESS+user.getUserName());
            return "redirect:/login";
        }
        else{
            model.addAttribute("isFailure",true);
            model.addAttribute("signupMsg",signup_err);
            }


       // logger.error("---Finishing signupNewUser---");
       return "signup";

    }
}
