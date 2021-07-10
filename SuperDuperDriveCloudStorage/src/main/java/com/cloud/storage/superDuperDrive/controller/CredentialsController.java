package com.cloud.storage.superDuperDrive.controller;

import com.cloud.storage.superDuperDrive.model.Credentials;
import com.cloud.storage.superDuperDrive.model.Users;
import com.cloud.storage.superDuperDrive.services.CredentialsService;
import com.cloud.storage.superDuperDrive.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
public class CredentialsController {

    private UsersService userService;
    private CredentialsService credentailService;

    @Autowired
    public CredentialsController(UsersService userService, CredentialsService credentialService) {
        this.userService = userService;
        this.credentailService = credentialService;
    }

    @PostMapping("/credential")
    public String postCredential(Authentication authentication, @ModelAttribute("credential") Credentials credential,
                                 RedirectAttributes redirectAttributes) {
        try {
            Users appUser = userService.getUser(authentication.getName());
            Integer userId = appUser.getUserid();
            credential.setUserId(userId);

            if (credential.getCredentialId() == null)
                credentailService.createCredential(credential);
            else
                credentailService.updateCredential(credential);

            redirectAttributes.addFlashAttribute("message", "Create Credential Success !");
            return "redirect:/home";
        } catch (Exception e) {
            redirectAttributes.addAttribute("errorMessage", e.getCause().getMessage());
            return "redirect:/result?error";
        }
    }

    @PostMapping("/remove-credential")
    public String removeNote(@ModelAttribute("credential") Credentials credential, RedirectAttributes redirectAttributes) {
        try {
            credentailService.removeCredential(credential.getCredentialId());
            redirectAttributes.addFlashAttribute("message", "Credential Already Remove !");
            return "redirect:/home";
        } catch (Exception e) {
            redirectAttributes.addAttribute("errorMessage", e.getCause().getMessage());
            return "redirect:/result?error";
        }
    }
}
