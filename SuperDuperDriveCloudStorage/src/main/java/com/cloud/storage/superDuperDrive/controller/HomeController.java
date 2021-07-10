package com.cloud.storage.superDuperDrive.controller;

import com.cloud.storage.superDuperDrive.model.Credentials;
import com.cloud.storage.superDuperDrive.model.Files;
import com.cloud.storage.superDuperDrive.model.Notes;
import com.cloud.storage.superDuperDrive.model.Users;
import com.cloud.storage.superDuperDrive.services.CredentialsService;
import com.cloud.storage.superDuperDrive.services.FilesService;
import com.cloud.storage.superDuperDrive.services.NotesService;
import com.cloud.storage.superDuperDrive.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {
    private UsersService userService;
    private NotesService noteService;
    private CredentialsService credentialService;
    private FilesService fileService;

    @Autowired
    public HomeController(UsersService userService,NotesService noteService, CredentialsService credentialService, FilesService fileService) {
        this.userService=userService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.fileService = fileService;
    }

    @GetMapping(value = {"/", "/home"})
    public String getHome(Authentication authentication,
                          @ModelAttribute("note") Notes note,
                          @ModelAttribute("credential") Credentials credential,
                          @ModelAttribute("file") Files file,
                          Model model){
        Users appUser = userService.getUser(authentication.getName());
        Integer userId = appUser.getUserid();

        model.addAttribute("notes",noteService.getNotesByUserId(userId));
        model.addAttribute("credentials",credentialService.getCredentialsByUserId(userId));
        model.addAttribute("files",fileService.getFilesByUserId(userId));

        return "home";
    }
}
