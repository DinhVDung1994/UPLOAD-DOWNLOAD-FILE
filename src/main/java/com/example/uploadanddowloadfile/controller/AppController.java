package com.example.uploadanddowloadfile.controller;

import com.example.uploadanddowloadfile.models.Document;
import com.example.uploadanddowloadfile.service.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping(value = "/api/v1/file")
public class AppController {

    @Autowired
    private IDocumentService documentService;


    @GetMapping("/")
    public String viewHomePage(Model model){
        List<Document> listDocs = documentService.getAllFile();
        model.addAttribute("listDocs",listDocs);
        return "home";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("document")MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
        documentService.uploadFile(file);
        redirectAttributes.addFlashAttribute("message","The file has been upload successfully!");
        return "redirect:/api/v1/file/";
    }

    @GetMapping("/download")
    public void downloadFile(@Param("id") Long id,HttpServletResponse response) throws Exception {
      documentService.downloadFile(id,response);
    }
}
