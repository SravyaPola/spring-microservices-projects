package com.synex.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;

@Controller
public class FileController {

    private final Path fileStorageLocation;

    @Autowired
    public FileController() throws IOException {
        this.fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();
        Files.createDirectories(this.fileStorageLocation);
    }

    @GetMapping("/")
    public String home(Model model) {
        try {
            model.addAttribute("files", Files.list(fileStorageLocation)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .toArray(String[]::new));
        } catch (IOException e) {
            model.addAttribute("files", new String[0]);
        }
        return "index"; // -> /WEB-INF/views/index.jsp
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        model.addAttribute("message", "Uploaded successfully: " + fileName);
        return "redirect:/";
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws MalformedURLException {
        Path filePath = fileStorageLocation.resolve(fileName).normalize();
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
