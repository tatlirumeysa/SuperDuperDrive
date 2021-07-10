package com.cloud.storage.superDuperDrive.controller;

import com.cloud.storage.superDuperDrive.model.Files;
import com.cloud.storage.superDuperDrive.model.Users;
import com.cloud.storage.superDuperDrive.services.FilesService;
import com.cloud.storage.superDuperDrive.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping
public class FileController {
    private UsersService usersService;
    private FilesService fileService;

    @Autowired
    public FileController(UsersService usersService, FilesService fileService) {
        this.usersService = usersService;
        this.fileService = fileService;
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable("fileId") Integer fileId) {
        Files file = fileService.getFileById(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename\"" + file.getFileName() + "\"")
                .body(new ByteArrayResource(file.getFileData()));
    }


    @PostMapping("/file")
    public String postCredential(Authentication authentication, MultipartFile fileToUpload,
                                 RedirectAttributes redirectAttributes)
            throws IOException {
        try {
            if(fileToUpload.isEmpty()){
                redirectAttributes.addFlashAttribute("errorMessage", "You have to select a file to upload.");
                return "redirect:/result?error";
            }
            if (fileService.getFilesByFileName(fileToUpload.getOriginalFilename()) != null)
            {
                redirectAttributes.addFlashAttribute("errorMessage", "File already exists.");
                return "redirect:/result?error";
            }
            Users appUser = usersService.getUser(authentication.getName());
            Integer userId = appUser.getUserid();
            fileService.uploadFile(fileToUpload, userId);
            redirectAttributes.addFlashAttribute("message", "File Upload Success");
            return "redirect:/home";
        } catch (Exception e) {
            redirectAttributes.addAttribute("errorMessage", e.getCause().getMessage());
            return "redirect:/result?error";
        }
    }

    @PostMapping("/remove-file")
    public String removeNote(@ModelAttribute("file") Files file, RedirectAttributes redirectAttributes) {
        try {
            fileService.removeFile(file.getFileId());
            redirectAttributes.addFlashAttribute("message", "File Already Remove !");
            return "redirect:/home";
        } catch (Exception e) {
            redirectAttributes.addAttribute("errorMessage", e.getCause().getMessage());
            return "redirect:/result?error";
        }
    }

}
