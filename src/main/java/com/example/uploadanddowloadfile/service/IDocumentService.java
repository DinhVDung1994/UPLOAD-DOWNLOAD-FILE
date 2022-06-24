package com.example.uploadanddowloadfile.service;

import com.example.uploadanddowloadfile.models.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface IDocumentService {
    Document uploadFile(MultipartFile file) throws IOException;

    List<Document> getAllFile();

    void downloadFile(Long id, HttpServletResponse response) throws Exception;
}
